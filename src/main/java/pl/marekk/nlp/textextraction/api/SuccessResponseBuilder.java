package pl.marekk.nlp.textextraction.api;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.marekk.nlp.textextraction.domain.TextExtractionSuccessResponse;

import java.util.Map;

import static java.util.Map.of;

@UtilityClass
@Slf4j
public class SuccessResponseBuilder {
  static ResponseEntity<Map<String, Object>> buildSuccessRestResponse(
      TextExtractionSuccessResponse successResponse) {
    log.info("preparing success response");
    Map<String, Object> body = of("text", successResponse.text());
    return new ResponseEntity<>(body, HttpStatus.OK);
  }
}
