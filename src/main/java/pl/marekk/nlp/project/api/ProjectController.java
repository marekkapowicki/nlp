package pl.marekk.nlp.project.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marekk.nlp.project.domain.Project;
import pl.marekk.nlp.project.domain.ProjectDomainService;

import java.util.UUID;

import static pl.marekk.nlp.project.api.ProjectDtoConverter.convert;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/projects")
@Slf4j
public class ProjectController {
    private final ProjectDomainService projectDomainService;

    @PostMapping
    ProjectDto create(@Validated @RequestBody RestCreateCommandRequest request) {
        log.info("creating the project {}", request);
        Project project = projectDomainService.createNew(request.toCreateProjectCommand());
        return convert(project);
    }

    @GetMapping(value = "/{id}")
    ProjectDto findById(@PathVariable("id") UUID projectId) {
        log.info("finding project by id {}", projectId);
        return projectDomainService.findById(projectId)
                .map(ProjectDtoConverter::convert)
                .orElse(null);
    }
}
