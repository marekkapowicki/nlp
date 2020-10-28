package pl.marekk.nlp.dataset.api;

import lombok.Value;
import pl.marekk.nlp.dataset.domain.Dataset;
import pl.marekk.nlp.dataset.domain.Document;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Value
public class EnhancedDatasetDto {
    DatasetDto datasetDto;
    List<DocumentDto> documents;

    public static EnhancedDatasetDto of(Dataset dataset, List<Document> documents) {
        List<DocumentDto> documentDtos =
                isEmpty(documents)
                        ? null
                        : documents.stream().map(DocumentDto::of).collect(Collectors.toList());
        return new EnhancedDatasetDto(DatasetDtoConverter.convert(dataset), documentDtos);
    }
}
