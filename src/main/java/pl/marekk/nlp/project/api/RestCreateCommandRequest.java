package pl.marekk.nlp.project.api;

import lombok.Data;
import lombok.ToString;
import pl.marekk.nlp.project.domain.CreateProjectCommand;

import javax.validation.constraints.NotEmpty;

@ToString
@Data
public class RestCreateCommandRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    public CreateProjectCommand toCreateProjectCommand() {
        return new CreateProjectCommand(name, description);
    }
}
