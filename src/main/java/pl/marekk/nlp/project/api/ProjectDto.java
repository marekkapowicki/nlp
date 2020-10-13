package pl.marekk.nlp.project.api;

import lombok.Value;

import java.util.UUID;

@Value
public class ProjectDto {
    UUID uuid;
    String name;
    String description;
}
