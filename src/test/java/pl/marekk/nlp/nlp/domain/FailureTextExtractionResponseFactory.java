package pl.marekk.nlp.nlp.domain;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import pl.marekk.nlp.ocr.domain.TextExtractionCommand;
import pl.marekk.nlp.ocr.domain.TextExtractionResponseFactory;

import java.util.function.Function;

@AllArgsConstructor
class FailureTextExtractionResponseFactory implements TextExtractionResponseFactory {
  private final int expectedCode;
  private final String expectedMessage;
  static Function<TextExtractionCommand, TextExtractionResponseFactory> newInstance(int expectedCode, String expectedMessage){
    return ocrCommand -> new FailureTextExtractionResponseFactory(expectedCode, expectedMessage);
  }
  @Override
  public boolean isSuccessful() {
    return false;
  }

  @Override
  public int code() {
    return expectedCode;
  }

  @Override
  public String message() {
    return expectedMessage;
  }

  @Override
  public String bodyAsString() {
    throw new NotImplementedException("no way to invoke");
  }

  @Override
  public void closeStream() {

  }
}
