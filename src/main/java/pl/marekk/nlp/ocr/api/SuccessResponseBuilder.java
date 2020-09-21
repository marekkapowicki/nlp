package pl.marekk.nlp.ocr.api;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.marekk.nlp.ocr.domain.OcrSuccessResponse;

import java.util.Map;

import static java.util.Map.of;

@UtilityClass
@Slf4j
public class SuccessResponseBuilder {
  static ResponseEntity<Map<String, Object>> buildSuccessRestResponse(
          OcrSuccessResponse successResponse) {
    log.info("preparing success response");
    Map<String, Object> body = of("text", successResponse.text());
    return new ResponseEntity<>(body, HttpStatus.OK);
  }
}
