package pl.marekk.nlp.textextraction.domain;

import io.vavr.control.Either;
import org.springframework.util.StringUtils;

import static pl.marekk.nlp.textextraction.domain.TextExtractionFailureResponse.failure;
import static pl.marekk.nlp.textextraction.domain.TextExtractionSuccessResponse.success;

public interface TextExtractionResponseFactory {
  default boolean isEmpty(String body) {
    return StringUtils.isEmpty(body.trim());
  }

  default Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> createResponse() {
    try {
      if (!isSuccessful()) {
        return Either.left(failure(code(), message()));
      }
      String bodyAsString = bodyAsString();
      if (isEmpty(bodyAsString)) {
        return Either.left(failure(code(), "extracted text is empty"));
      }
      return Either.right(success(bodyAsString));
    } catch (IllegalStateException e) {
      return Either.left(failure(e));
    } finally {
      closeStream();
    }
  }

  boolean isSuccessful();

  int code();

  String message();

  String bodyAsString();

  void closeStream();
}
