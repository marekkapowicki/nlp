package pl.marekk.nlp.dataset.api;

import io.vavr.Tuple2;
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
import pl.marekk.nlp.dataset.domain.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
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

    @PostMapping(value = "/{datasetId}/documents")
    DocumentDto addDocument(@PathVariable("datasetId") UUID datasetId,
                            @Validated @RequestBody @NotNull RestCreateDocumentRequest request) {
        Dataset dataset = datasetFacade.findById(datasetId);
        Document document = dataset.addDocument(request.toCreateCreateDocumentCommand())._2;
        return DocumentDto.of(document);

    }

    @GetMapping(value = "/{id}")
    EnhancedDatasetDto findById(@PathVariable("id") UUID datasetId) {
        log.info("finding dataset by id {}", datasetId);
        Tuple2<Dataset, List<Document>> datasetTuple = datasetFacade.findById(datasetId).withDocuments();
        return EnhancedDatasetDto.of(datasetTuple._1, datasetTuple._2);
    }
}
