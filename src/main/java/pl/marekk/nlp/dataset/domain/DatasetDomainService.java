package pl.marekk.nlp.dataset.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DatasetDomainService {
    private final DatasetRepository datasetRepository;
    private final DatasetFactory datasetFactory;

    public static DatasetDomainService of(DatasetRepository datasetRepository) {
        return new DatasetDomainService(datasetRepository, new DatasetFactory());
    }

    public Dataset createNew(CreateDatasetCommand createDatasetCommand) {
        return datasetFactory.create(createDatasetCommand, datasetRepository).createNew();
    }

    public Optional<Dataset> findById(UUID uuid) {
        return datasetRepository.getById(uuid.toString())
                .map(dataset -> dataset.withDatasetRepository(datasetRepository));
    }
}
