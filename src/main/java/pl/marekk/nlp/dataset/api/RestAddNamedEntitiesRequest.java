package pl.marekk.nlp.dataset.api;

import lombok.Data;
import pl.marekk.nlp.dataset.domain.AddNamedEntitiesCommand;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class RestAddNamedEntitiesRequest {
  @NotNull
  private Set<RestAddNamedEntityRequest> entities;

  AddNamedEntitiesCommand toAddNamedEntitiesCommand(UUID documentId) {
    return new AddNamedEntitiesCommand(
            documentId,
            entities.stream()
                    .map(RestAddNamedEntityRequest::toAddNamedEntityCommand)
                    .collect(Collectors.toSet()));
  }

  @Data
  private static class RestAddNamedEntityRequest {
    private int start;
    private int end;
    private String label;

    AddNamedEntitiesCommand.AddNamedEntityCommand toAddNamedEntityCommand() {
      return new AddNamedEntitiesCommand.AddNamedEntityCommand(start, end, label);
    }
  }
}
