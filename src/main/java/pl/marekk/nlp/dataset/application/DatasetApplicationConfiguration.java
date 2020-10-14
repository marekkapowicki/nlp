package pl.marekk.nlp.dataset.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.marekk.nlp.dataset.domain.DatasetDomainService;
import pl.marekk.nlp.dataset.domain.DatasetRepository;

@Configuration
@EnableMongoRepositories
class DatasetApplicationConfiguration {

    @Bean
    DatasetFacade datasetFacade(DatasetDomainService datasetDomainService) {
        return new DatasetFacade(datasetDomainService);
    }

    @Bean
    DatasetDomainService datasetDomainService(DatasetRepository datasetRepository) {
        return DatasetDomainService.of(datasetRepository);
    }
}
