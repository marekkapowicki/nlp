package pl.marekk.nlp.dataset.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class Document {
    @Id
    private String id;

    @Indexed(unique = true)
    @Getter
    private String name;

    private String datasetId;
    @Getter
    private String text;

    private Set<NamedEntity> namedEntities;

    private Document(String id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    static Document of(UUID id, String name, String text) {
        return new Document(id.toString(), name, text);
    }

    Document withNamedEntities(Set<NamedEntity> namedEntities) {
        this.namedEntities = namedEntities;
        return this;
    }

    Document withDatasetId(String datasetId) {
        this.datasetId = datasetId;
        return this;
    }

    public UUID id() {
        return UUID.fromString(id);
    }
}
