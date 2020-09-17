package pl.marekk.nlp.ocr.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OcrApiConfig {
    @Bean
    MultipartFileToOcrRequestConverter multipartFileToOcrRequestConverter() {
        return new MultipartFileToOcrRequestConverter();
    }
}
