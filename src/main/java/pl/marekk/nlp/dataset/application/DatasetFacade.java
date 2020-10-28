package pl.marekk.nlp.dataset.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.nlp.dataset.application.exception.DatasetExceptions;
import pl.marekk.nlp.dataset.domain.AddNamedEntitiesCommand;
import pl.marekk.nlp.dataset.domain.CreateDatasetCommand;
import pl.marekk.nlp.dataset.domain.Dataset;
import pl.marekk.nlp.dataset.domain.DatasetDomainService;
import pl.marekk.nlp.dataset.domain.Document;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class DatasetFacade {
    private final DatasetDomainService datasetDomainService;

    public Dataset createNew(CreateDatasetCommand createDatasetCommand) {
        log.info("creating a new dataset {}", createDatasetCommand);
        return datasetDomainService.createNew(createDatasetCommand);
    }

    public Dataset findById(UUID datasetId) {
        log.info("finding dataset by id {}", datasetId);
        return datasetDomainService
                .findById(datasetId.toString())
                .orElseThrow(DatasetExceptions.notFound("dataset not found: " + datasetId.toString()));
    }

    public Document addNamedEntities(UUID datasetId, AddNamedEntitiesCommand command) {
        log.info("adding the entities {}, to document in dataset {}", command, datasetId);
        return findById(datasetId)
                .addNamedEntitiesToDocument(command)
                .orElseThrow(DatasetExceptions.notFound("document not found: " + command.documentId()));
    }
}
