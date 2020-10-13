package pl.marekk.nlp.project.domain;

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
@ToString(exclude = "projectRepository")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Project {

    static Project of(UUID uuid, String name, String description, ProjectRepository projectRepository) {
        return new Project(uuid.toString(), name, description, projectRepository);
    }

    @Id
    private String id;
    @Getter
    private String name;
    @Getter
    private String description;

    @Transient
    private ProjectRepository projectRepository;


    Project createNew() {
        log.info("create new project {}", this);
        return projectRepository.save(this).withProjectRepository(projectRepository);
    }

    private Project withProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        return this;
    }

    public UUID id() {
        return UUID.fromString(id);
    }
}
