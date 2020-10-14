package pl.marekk.nlp.dataset.domain;

import java.util.Optional;

public interface DatasetRepository {
    Dataset save(Dataset aggregate);

    Optional<Dataset> getById(String uuid);
}
