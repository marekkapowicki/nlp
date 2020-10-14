package pl.marekk.nlp.dataset.domain;

import lombok.Value;

@Value
public class CreateDatasetCommand {
    String name;
    String description;
}
