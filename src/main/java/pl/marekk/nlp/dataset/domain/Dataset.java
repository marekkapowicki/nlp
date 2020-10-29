package pl.marekk.nlp.dataset.domain;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@ToString(exclude = "datasetRepository")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Dataset {

    @Id
    private String id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Transient
    private DatasetRepository datasetRepository;
    @Transient
    private DocumentRepository documentRepository;

    static Dataset of(
            UUID uuid,
            String name,
            String description,
            DatasetRepository datasetRepository,
            DocumentRepository documentRepository) {
        return new Dataset(uuid.toString(), name, description, datasetRepository, documentRepository);
    }

    Dataset createNew() {
        log.info("create new project {}", this);
        return datasetRepository
                .save(this)
                .withDatasetRepository(datasetRepository)
                .withDocumentRepository(documentRepository);
    }

    Dataset withDatasetRepository(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
        return this;
    }

    Dataset withDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
        return this;
    }

    public Tuple2<Dataset, Document> addDocument(CreateDocumentCommand createDocumentCommand) {
        Document document =
                Document.of(
                        createDocumentCommand.getId(),
                        createDocumentCommand.getName(),
                        createDocumentCommand.getText())
                        .withDatasetId(id);
        Document saved = documentRepository.save(document);
        return Tuple.of(this, saved);
    }

    public Tuple2<Dataset, List<Document>> withDocuments() {
        List<Document> documents = documentRepository.getByDatasetId(id);
        return Tuple.of(this, documents);
    }

    public Optional<Document> addNamedEntitiesToDocument(AddNamedEntitiesCommand command) {
        return documentRepository
                .findByIdAndDatasetId(command.documentId(), id)
                .map(doc -> doc.withNamedEntities(command.toNamedEntity()));
    }

    public UUID id() {
        return UUID.fromString(id);
    }
}
