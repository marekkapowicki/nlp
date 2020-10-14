package pl.marekk.nlp.dataset.api;

import lombok.experimental.UtilityClass;
import pl.marekk.nlp.dataset.domain.Dataset;

@UtilityClass
class DatasetDtoConverter {
    static DatasetDto convert(Dataset dataset) {
        return new DatasetDto(dataset.id(), dataset.getName(), dataset.getDescription());
    }
}
