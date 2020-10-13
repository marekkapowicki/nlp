package pl.marekk.nlp.ocr.api;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.marekk.nlp.ocr.domain.TextExtractionFailureResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
@Slf4j
class FailureResponseBuilder {

  static ResponseEntity<Map<String, Object>> buildFailureResponse(
      TextExtractionFailureResponse failureResponse) {
    log.info("preparing failure response {} ", failureResponse.statusCode());
    return new ResponseEntity(buildFailureBody(failureResponse), statusCode(failureResponse));
  }

  private static HttpStatus statusCode(TextExtractionFailureResponse failureResponse) {
    return HttpStatus.valueOf(failureResponse.statusCode());
  }

  private static Map<String, Object> buildFailureBody(TextExtractionFailureResponse failureResponse) {
    Map<String, Object> errorAttributes = new HashMap<>();
    errorAttributes.put("timestamp", new Date());
    errorAttributes.put("status", failureResponse.statusCode());
    errorAttributes.put("error", error(failureResponse.statusCode()));
    errorAttributes.put("message", failureResponse.cause());
    return errorAttributes;
  }

  private static String error(int status) {
    try {
      return HttpStatus.valueOf(status).getReasonPhrase();
    } catch (Exception e) {
      return "Http Status " + status;
    }
  }
}
