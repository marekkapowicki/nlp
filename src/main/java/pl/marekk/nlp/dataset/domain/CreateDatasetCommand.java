package pl.marekk.nlp.dataset.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateDatasetCommand {
    UUID id;
    String name;
    String description;
}
