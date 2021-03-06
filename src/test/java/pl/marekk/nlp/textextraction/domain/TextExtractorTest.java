package pl.marekk.nlp.textextraction.domain;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class TextExtractorTest {
  private static TextExtractionInput sampleOcrCommand() {
    return new TextExtractionInput(new byte[1], "contentType", null);
  }

  @Test
  public void shouldReturnSuccessResponse() {
    // Given
    TextExtractor textExtractor = TestTextExtractor.success("some text");
    TextExtractionSuccessResponse expectedValue =
        TextExtractionSuccessResponse.success("some text");
    // When
    Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> response =
        textExtractor.extractText(sampleOcrCommand());
    // Then

    assertThat(response).usingFieldByFieldValueComparator().containsOnRight(expectedValue);
  }

  @Test
  public void shouldReturnFailureResponse() {
    // Given
    TextExtractor textExtractor = TestTextExtractor.failure(503, "some text");
    TextExtractionFailureResponse expectedValue =
        TextExtractionFailureResponse.failure(503, "some text");
    // When
    Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> response =
        textExtractor.extractText(sampleOcrCommand());
    // Then

    assertThat(response).usingFieldByFieldValueComparator().containsOnLeft(expectedValue);
  }
}
