package pl.marekk.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.marekk.nlp.documentation.SwaggerConfiguration;

@SpringBootApplication
@Import({SwaggerConfiguration.class})
public class NlpApplication {

    public static void main(String[] args) {
        SpringApplication.run(NlpApplication.class, args);
    }
}
