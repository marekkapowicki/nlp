package pl.marekk.nlp.ocr.application;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.marekk.nlp.ocr.domain.Ocr;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@EnableConfigurationProperties(TikaOcrProperties.class)
@Slf4j
class OcrApplicationConfiguration {

  @Bean
  HttpLoggingInterceptor loggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(TikaOcrClient.httpLogger);
    interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
    return interceptor;
  }

  @Bean
  TikaOcrClient tikaOcrClient(
          TikaOcrProperties tikaOcrProperties, HttpLoggingInterceptor loggingInterceptor) {

    OkHttpClient httpClient =
            new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(tikaOcrProperties.getCallTimeoutInSecond(), SECONDS)
                    .callTimeout(tikaOcrProperties.getCallTimeoutInSecond(), SECONDS)
                    .build();
    return new TikaOcrClient(
            httpClient, tikaOcrProperties.getServerUrl(), tikaOcrProperties.getTikaHeaders());
  }

  @Bean
  Ocr ocr(TikaOcrClient tikaOcrClient) {
    return new Ocr(tikaOcrClient);
  }
}
