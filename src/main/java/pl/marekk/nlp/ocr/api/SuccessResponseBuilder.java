package pl.marekk.nlp.ocr.api;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.marekk.nlp.ocr.domain.OcrSuccessResponse;

import java.util.Map;

import static java.util.Map.of;

@UtilityClass
public class SuccessResponseBuilder {
  static ResponseEntity<Map<String, Object>> buildSuccessRestResponse(
          OcrSuccessResponse successResponse) {
    Map<String, Object> body = of("text", successResponse.text());
    return new ResponseEntity<>(body, HttpStatus.OK);
  }
}
