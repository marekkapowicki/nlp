package pl.marekk.nlp.textextraction.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextExtractionSuccessResponse {
  private final String text;

  public static TextExtractionSuccessResponse success(String text) {
    return new TextExtractionSuccessResponse(text);
  }

  public String text() {
    return text;
  }
}
