package pl.marekk.nlp.dataset.api;

import lombok.Value;
import pl.marekk.nlp.dataset.domain.Document;

import java.util.UUID;

@Value
public class DocumentDto {
    UUID uuid;
    String name;
    String text;

    public static DocumentDto of(Document document) {
        return DocumentDtoConverter.convert(document);
    }
}
