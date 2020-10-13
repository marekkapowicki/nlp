package pl.marekk.nlp.project.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.marekk.nlp.project.domain.ProjectDomainService;
import pl.marekk.nlp.project.domain.ProjectRepository;

@Configuration
@EnableMongoRepositories
class ProjectApplicationConfiguration {

    @Bean
    ProjectDomainService projectDomainService(ProjectRepository projectRepository) {
        return ProjectDomainService.of(projectRepository);
    }
}
