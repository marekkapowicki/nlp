package pl.marekk.nlp.textextraction.domain;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@AllArgsConstructor
@Slf4j
public class TextExtractor {
  private final Function<TextExtractionCommand, TextExtractionResponseFactory>
      textExtractionFunction;

  private static void logNoOcrFailure(TextExtractionFailureResponse failureResponse) {
    log.info("no ocr result {}", failureResponse);
  }

  public Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> extractText(
      TextExtractionInput textExtractionInput) {
    Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> noOcrResponse =
        noOcrExtraction(textExtractionInput);
    return noOcrResponse.fold(
        error -> {
          logNoOcrFailure(error);
          return ocrExtraction(textExtractionInput);
        },
        success -> noOcrResponse);
  }

  private Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> noOcrExtraction(
      TextExtractionInput textExtractionInput) {
    log.info("try to extract the text without ocr {}", textExtractionInput);
    TextExtractionCommand ocrCommand = TextExtractionCommand.noOcrCommand(textExtractionInput);
    return textExtractionFunction.apply(ocrCommand).createResponse();
  }

  private Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> ocrExtraction(
      TextExtractionInput textExtractionInput) {
    log.info("try to extract the text with ocr {}", textExtractionInput);
    TextExtractionCommand ocrCommand = TextExtractionCommand.ocrCommand(textExtractionInput);
    return textExtractionFunction.apply(ocrCommand).createResponse();
  }
}
