package pl.marekk.nlp.nlp.domain;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import pl.marekk.nlp.ocr.domain.OcrCommand;
import pl.marekk.nlp.ocr.domain.OcrResponseFactory;

import java.util.function.Function;

@AllArgsConstructor
class FailureOcrResponseFactory implements OcrResponseFactory {
  private final int expectedCode;
  private final String expectedMessage;
  static Function<OcrCommand, OcrResponseFactory> newInstance(int expectedCode, String expectedMessage){
    return ocrCommand -> new FailureOcrResponseFactory(expectedCode, expectedMessage);
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
