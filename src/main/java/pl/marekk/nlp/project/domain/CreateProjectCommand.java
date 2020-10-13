package pl.marekk.nlp.project.domain;

import lombok.Value;

@Value
public class CreateProjectCommand {
    String name;
    String description;
}
