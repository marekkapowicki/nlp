package pl.marekk.nlp.dataset.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

@Slf4j
@ToString(exclude = "datasetRepository")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Dataset {

    static Dataset of(UUID uuid, String name, String description, DatasetRepository datasetRepository) {
        return new Dataset(uuid.toString(), name, description, datasetRepository);
    }

    @Id
    private String id;
    @Getter
    private String name;
    @Getter
    private String description;

    @Transient
    private DatasetRepository datasetRepository;


    Dataset createNew() {
        log.info("create new project {}", this);
        return datasetRepository.save(this).withDatasetRepository(datasetRepository);
    }

    Dataset withDatasetRepository(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
        return this;
    }

    public UUID id() {
        return UUID.fromString(id);
    }
}
