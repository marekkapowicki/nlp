package pl.marekk.nlp.dataset.domain;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class DatasetFactory {
    Dataset create(CreateDatasetCommand createDatasetCommand, DatasetRepository datasetRepository) {
        return Dataset.of(UUID.randomUUID(), createDatasetCommand.getName(), createDatasetCommand.getDescription(), datasetRepository);
    }
}
