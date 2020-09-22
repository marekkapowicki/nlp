package pl.marekk.nlp.ocr.domain;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class Ocr {
  private final Function<OcrCommand, OcrResponseFactory> ocrFunction;

  public Either<OcrFailureResponse, OcrSuccessResponse> processOcr(OcrCommand ocrCommand) {
    final OcrResponseFactory ocrResponseFactory = ocrFunction.apply(ocrCommand);
    return ocrResponseFactory.createResponse();
  }
}
