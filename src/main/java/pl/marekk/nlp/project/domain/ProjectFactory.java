package pl.marekk.nlp.project.domain;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
class ProjectFactory {
    Project create(CreateProjectCommand createProjectCommand, ProjectRepository projectRepository) {
        return Project.of(UUID.randomUUID(), createProjectCommand.getName(), createProjectCommand.getDescription(), projectRepository);
    }
}
