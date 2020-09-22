package pl.marekk.nlp.ocr.domain;


import io.vavr.control.Either;
import org.junit.Test;
import pl.marekk.nlp.nlp.domain.TestOcr;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

public class OcrTest {
  @Test
  public void shouldReturnSuccessResponse() {
      // Given
      Ocr ocr = TestOcr.success("some text");
      OcrSuccessResponse expectedValue = OcrSuccessResponse.success("some text");
      // When
      Either<OcrFailureResponse, OcrSuccessResponse> response = ocr.processOcr(sampleOcrCommand());
      // Then

      assertThat(response)
              .usingFieldByFieldValueComparator()
              .containsOnRight(expectedValue);
  }
    @Test
    public void shouldReturnFailureResponse() {
        // Given
        Ocr ocr = TestOcr.failure(503, "some text");
        OcrFailureResponse expectedValue = OcrFailureResponse.failure(503, "some text");
        // When
        Either<OcrFailureResponse, OcrSuccessResponse> response = ocr.processOcr(sampleOcrCommand());
        // Then

        assertThat(response)
                .usingFieldByFieldValueComparator()
                .containsOnLeft(expectedValue);
    }

    private static OcrCommand sampleOcrCommand() {
        return new OcrCommand(new byte[1], "contentType", null);
    }
}