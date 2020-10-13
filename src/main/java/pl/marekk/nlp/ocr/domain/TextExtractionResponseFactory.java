package pl.marekk.nlp.ocr.domain;

import io.vavr.control.Either;
import org.springframework.util.StringUtils;

import static pl.marekk.nlp.ocr.domain.TextExtractionFailureResponse.failure;
import static pl.marekk.nlp.ocr.domain.TextExtractionSuccessResponse.success;

public interface TextExtractionResponseFactory {

  default boolean isEmpty(){
    return StringUtils.isEmpty(bodyAsString().trim());
  }
  default Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> createResponse() {
    try {
      if (!isSuccessful()) {
        return Either.left(failure(code(), message()));
      }
      return Either.right(success(bodyAsString()));
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
