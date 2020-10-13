package pl.marekk.nlp.project.api;

import lombok.experimental.UtilityClass;
import pl.marekk.nlp.project.domain.Project;

@UtilityClass
class ProjectDtoConverter {
    static ProjectDto convert(Project project) {
        return new ProjectDto(project.id(), project.getName(), project.getDescription());
    }
}
