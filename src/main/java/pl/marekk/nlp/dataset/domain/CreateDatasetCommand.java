package pl.marekk.nlp.dataset.domain;

import lombok.ToString;
import lombok.Value;

import java.util.UUID;

@Value
@ToString
public class CreateDatasetCommand {
    UUID id;
    String name;
    String description;
}
