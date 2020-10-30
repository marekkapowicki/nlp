package pl.marekk.nlp.dataset.api;

import lombok.Value;
import pl.marekk.nlp.dataset.domain.Document;
import pl.marekk.nlp.dataset.domain.NamedEntity;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class EnhancedDocumentDto {
  UUID id;
  String name;
  String datasetId;
  String text;
  Set<NamedEntityDto> namedEntities;

  static EnhancedDocumentDto of(Document document) {
    return new EnhancedDocumentDto(
            document.id(),
            document.getName(),
            document.getDatasetId(),
            document.getText(),
            NamedEntityDto.of(document.getNamedEntities()));
  }

  @Value
  private static class NamedEntityDto {
    int start;
    int end;
    String label;

    static Set<NamedEntityDto> of(Set<NamedEntity> namedEntities) {
      return namedEntities == null
              ? null
              : namedEntities.stream().map(NamedEntityDto::of).collect(Collectors.toSet());
    }

    static NamedEntityDto of(NamedEntity namedEntity) {
      return new NamedEntityDto(
              namedEntity.getStart(), namedEntity.getEnd(), namedEntity.getLabel());
    }
  }
}
