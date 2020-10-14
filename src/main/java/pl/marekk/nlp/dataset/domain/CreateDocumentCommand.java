package pl.marekk.nlp.dataset.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateDocumentCommand {
    UUID id;
    String name;
    String text;
}
