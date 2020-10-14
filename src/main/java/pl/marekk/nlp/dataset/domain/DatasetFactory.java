package pl.marekk.nlp.dataset.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class DatasetFactory {
    Dataset create(CreateDatasetCommand createDatasetCommand, DatasetRepository datasetRepository, DocumentRepository documentRepository) {
        return Dataset.of(createDatasetCommand.getId(), createDatasetCommand.getName(), createDatasetCommand.getDescription(), datasetRepository, documentRepository);
    }
}
