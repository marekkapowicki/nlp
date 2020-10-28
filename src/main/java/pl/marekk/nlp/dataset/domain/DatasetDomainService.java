package pl.marekk.nlp.dataset.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DatasetDomainService {
    private final DatasetRepository datasetRepository;
    private final DocumentRepository documentRepository;
    private final DatasetFactory datasetFactory;

    public static DatasetDomainService of(
            DatasetRepository datasetRepository, DocumentRepository documentRepository) {
        return new DatasetDomainService(datasetRepository, documentRepository, new DatasetFactory());
    }

    public Dataset createNew(CreateDatasetCommand createDatasetCommand) {
        return datasetFactory
                .create(createDatasetCommand, datasetRepository, documentRepository)
                .createNew();
    }

    public Optional<Dataset> findById(String datasetId) {
        return datasetRepository
                .getById(datasetId)
                .map(
                        dataset ->
                                dataset
                                        .withDatasetRepository(datasetRepository)
                                        .withDocumentRepository(documentRepository));
    }
}
