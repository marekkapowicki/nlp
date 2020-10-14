package pl.marekk.nlp.dataset.application;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.marekk.nlp.dataset.domain.Document;
import pl.marekk.nlp.dataset.domain.DocumentRepository;

import java.util.List;
import java.util.UUID;

public interface MongoDocumentRepository
        extends MongoRepository<Document, UUID>, DocumentRepository {
    List<Document> getByDatasetId(String datasetId);
}
