package pl.marekk.nlp.dataset.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.marekk.nlp.dataset.application.exception.DatasetExceptions;
import pl.marekk.nlp.dataset.application.log.LogMethodInvocation;
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

    @LogMethodInvocation
    public Dataset createNew(CreateDatasetCommand createDatasetCommand) {
        log.info("creating a new dataset {}", createDatasetCommand);
        return datasetDomainService.createNew(createDatasetCommand);
    }

    @LogMethodInvocation
    public Dataset findById(UUID datasetId) {
        log.info("finding dataset by id {}", datasetId);
        return datasetDomainService
                .findById(datasetId.toString())
                .orElseThrow(DatasetExceptions.notFound("dataset not found: " + datasetId.toString()));
    }

    @LogMethodInvocation
    public Document addNamedEntities(UUID datasetId, AddNamedEntitiesCommand command) {
        log.info("adding the entities {}, to document in dataset {}", command, datasetId);
        return findById(datasetId)
                .addNamedEntitiesToDocument(command)
                .orElseThrow(DatasetExceptions.notFound("document not found: " + command.documentId()));
    }

    @LogMethodInvocation
    public Document findDocumentById(UUID datasetId, UUID documentId) {
        log.info("finding document in dataset {} by id {}", datasetId, datasetId);
        return findById(datasetId)
                .findDocumentById(documentId.toString())
                .orElseThrow(DatasetExceptions.notFound("document not found: " + documentId));
    }
}
