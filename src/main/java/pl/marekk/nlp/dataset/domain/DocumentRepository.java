package pl.marekk.nlp.dataset.domain;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository {
    Document save(Document document);

    Optional<Document> getById(String uuid);

    List<Document> getByDatasetId(String datasetId);
}
