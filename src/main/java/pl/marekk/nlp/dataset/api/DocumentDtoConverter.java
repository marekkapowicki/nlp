package pl.marekk.nlp.dataset.api;

import lombok.experimental.UtilityClass;
import pl.marekk.nlp.dataset.domain.Document;

@UtilityClass
class DocumentDtoConverter {
    static DocumentDto convert(Document document) {
        return new DocumentDto(document.id(), document.getName(), document.getText());
    }
}
