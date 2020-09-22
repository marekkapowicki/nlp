package pl.marekk.nlp.ocr.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OcrFailureResponse {
  private final int statusCode;
  private final String cause;

  public static OcrFailureResponse failure(Exception e) {
    return new OcrFailureResponse(503, e.getMessage());
  }

  public static OcrFailureResponse failure(int statusCode, String cause) {
    return new OcrFailureResponse(statusCode, cause);
  }

  public String cause() {
    return cause;
  }

  public int statusCode() {
    return statusCode;
  }
}
