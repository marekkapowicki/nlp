package pl.marekk.nlp.ocr.application;

import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.marekk.nlp.ocr.domain.Ocr;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@EnableConfigurationProperties(TikaOcrProperties.class)
class OcrApplicationConfiguration {

    @Bean
    TikaOcrClient tikaOcrClient(TikaOcrProperties tikaOcrProperties) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(tikaOcrProperties.getCallTimeoutInSecond(), SECONDS)
                .callTimeout(tikaOcrProperties.getCallTimeoutInSecond(), SECONDS).build();
        return new TikaOcrClient(httpClient, tikaOcrProperties.getServerUrl(), tikaOcrProperties.getTikaHeaders());
    }

    @Bean
    Ocr ocr(TikaOcrClient tikaOcrClient) {
        return new Ocr(tikaOcrClient);
    }
}
