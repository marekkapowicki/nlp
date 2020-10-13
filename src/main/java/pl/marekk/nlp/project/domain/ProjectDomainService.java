package pl.marekk.nlp.project.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectDomainService {
    private final ProjectRepository projectRepository;
    private final ProjectFactory projectFactory;

    public static ProjectDomainService of(ProjectRepository projectRepository) {
        return new ProjectDomainService(projectRepository, new ProjectFactory());
    }

    public Project createNew(CreateProjectCommand createProjectCommand) {
        return projectFactory.create(createProjectCommand, projectRepository).createNew();
    }

    public Optional<Project> findById(UUID uuid) {
        return projectRepository.getById(uuid.toString());
    }
}
