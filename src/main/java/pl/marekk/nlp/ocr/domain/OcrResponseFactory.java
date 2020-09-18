package pl.marekk.nlp.ocr.domain;

import io.vavr.control.Either;

import static pl.marekk.nlp.ocr.domain.OcrFailureResponse.failure;
import static pl.marekk.nlp.ocr.domain.OcrSuccessResponse.success;

public interface OcrResponseFactory {

    default Either<OcrFailureResponse, OcrSuccessResponse> createResponse() {
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
