package pl.marekk.nlp.dataset.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marekk.nlp.dataset.application.DatasetFacade;
import pl.marekk.nlp.dataset.domain.Dataset;

import java.util.UUID;

import static pl.marekk.nlp.dataset.api.DatasetDtoConverter.convert;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/datasets")
@Slf4j
public class DatasetController {
    private final DatasetFacade datasetFacade;

    @PostMapping
    DatasetDto create(@Validated @RequestBody RestCreateDatasetRequest request) {
        log.info("creating the dataset {}", request);
        Dataset dataset = datasetFacade.createNew(request.toCreateDatasetCommand());
        return convert(dataset);
    }

    @GetMapping(value = "/{id}")
    DatasetDto findById(@PathVariable("id") UUID datasetId) {
        log.info("finding dataset by id {}", datasetId);
        return convert(datasetFacade.findById(datasetId));
    }
}
