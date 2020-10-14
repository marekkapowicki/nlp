package pl.marekk.nlp.dataset.api;

import lombok.Value;

import java.util.UUID;

@Value
public class DatasetDto {
    UUID uuid;
    String name;
    String description;
}
