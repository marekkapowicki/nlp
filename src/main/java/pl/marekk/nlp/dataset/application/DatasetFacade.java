package pl.marekk.nlp.dataset.application;

import lombok.AllArgsConstructor;
import pl.marekk.nlp.dataset.application.exception.DatasetExceptions;
import pl.marekk.nlp.dataset.domain.CreateDatasetCommand;
import pl.marekk.nlp.dataset.domain.Dataset;
import pl.marekk.nlp.dataset.domain.DatasetDomainService;

import java.util.UUID;

@AllArgsConstructor
public class DatasetFacade {
    private final DatasetDomainService datasetDomainService;

    public Dataset createNew(CreateDatasetCommand createDatasetCommand) {
        return datasetDomainService.createNew(createDatasetCommand);
    }

    public Dataset findById(UUID datasetId) {
        return datasetDomainService.findById(datasetId.toString())
                .orElseThrow(DatasetExceptions.notFound("dataset not found: " + datasetId.toString()));
    }
}
