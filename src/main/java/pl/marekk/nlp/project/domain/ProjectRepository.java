package pl.marekk.nlp.project.domain;

import java.util.Optional;

public interface ProjectRepository {
    Project save(Project aggregate);

    Optional<Project> getById(String uuid);
}
