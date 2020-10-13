package pl.marekk.nlp.textextraction.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class TextExtractionFailureResponse {
  private final int statusCode;
  private final String cause;

  public static TextExtractionFailureResponse failure(Exception e) {
    return new TextExtractionFailureResponse(503, e.getMessage());
  }

  public static TextExtractionFailureResponse failure(int statusCode, String cause) {
    return new TextExtractionFailureResponse(statusCode, cause);
  }

  public String cause() {
    return cause;
  }

  public int statusCode() {
    return statusCode;
  }
}
