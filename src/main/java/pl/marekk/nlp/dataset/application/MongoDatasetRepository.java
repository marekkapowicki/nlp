package pl.marekk.nlp.dataset.application;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.marekk.nlp.dataset.domain.Dataset;
import pl.marekk.nlp.dataset.domain.DatasetRepository;

import java.util.UUID;

public interface MongoDatasetRepository extends MongoRepository<Dataset, UUID>, DatasetRepository {
}
