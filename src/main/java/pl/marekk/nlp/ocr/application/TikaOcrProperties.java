package pl.marekk.nlp.ocr.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Map;

@ConfigurationProperties(prefix = "ocr")
@Validated
@Getter
@Setter
public class TikaOcrProperties {
  @NotNull
  private String serverUrl;

    private int callTimeoutInSecond;

    private Map<String, String> tikaHeaders;
}
