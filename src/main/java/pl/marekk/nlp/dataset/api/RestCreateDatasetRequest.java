package pl.marekk.nlp.dataset.api;

import lombok.Data;
import lombok.ToString;
import pl.marekk.nlp.dataset.domain.CreateDatasetCommand;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@ToString
@Data
public class RestCreateDatasetRequest {
  @NotEmpty
  private String name;
  @NotEmpty
  private String description;

  public CreateDatasetCommand toCreateDatasetCommand() {
    return new CreateDatasetCommand(UUID.randomUUID(), name, description);
  }
}
