package pl.marekk.nlp.textextraction.domain;

import io.vavr.control.Either;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class TextExtractor {
  private final Function<TextExtractionCommand, TextExtractionResponseFactory> textExtractionFunction;

  public Either<TextExtractionFailureResponse, TextExtractionSuccessResponse> extractText(TextExtractionCommand textExtractionCommand) {
    final TextExtractionResponseFactory textExtractionResponseFactory = textExtractionFunction.apply(textExtractionCommand);
    return textExtractionResponseFactory.createResponse();
  }
}
