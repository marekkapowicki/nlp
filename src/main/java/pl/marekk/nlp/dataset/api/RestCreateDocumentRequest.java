package pl.marekk.nlp.dataset.api;

import lombok.Value;
import pl.marekk.nlp.dataset.domain.CreateDocumentCommand;

import java.util.UUID;

@Value
public class RestCreateDocumentRequest {
    String name;
    String text;

    public CreateDocumentCommand toCreateCreateDocumentCommand() {
        return new CreateDocumentCommand(UUID.randomUUID(), name, text);
    }
}
