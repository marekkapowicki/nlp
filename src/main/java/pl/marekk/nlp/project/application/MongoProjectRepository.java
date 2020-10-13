package pl.marekk.nlp.project.application;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.marekk.nlp.project.domain.Project;
import pl.marekk.nlp.project.domain.ProjectRepository;

import java.util.UUID;

public interface MongoProjectRepository extends MongoRepository<Project, UUID>, ProjectRepository {
}
