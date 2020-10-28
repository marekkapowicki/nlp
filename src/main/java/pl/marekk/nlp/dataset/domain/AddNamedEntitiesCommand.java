package pl.marekk.nlp.dataset.domain;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
public class AddNamedEntitiesCommand {
  private final UUID documentId;
  private final Set<AddNamedEntityCommand> addNamedEntityCommands;

  Set<NamedEntity> toNamedEntity() {
    return this.addNamedEntityCommands.stream()
            .map(AddNamedEntityCommand::toNamedEntity)
            .collect(Collectors.toSet());
  }

  public String documentId() {
    return documentId.toString();
  }

  @AllArgsConstructor
  public static class AddNamedEntityCommand {
    private final int start;
    private final int end;
    private final String label;

    NamedEntity toNamedEntity() {
      return new NamedEntity(start, end, label);
    }
  }
}
