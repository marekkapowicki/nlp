package pl.marekk.nlp.nlp.domain;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import pl.marekk.nlp.ocr.domain.OcrCommand;
import pl.marekk.nlp.ocr.domain.OcrResponseFactory;

import java.util.function.Function;

@AllArgsConstructor
class SuccessOcrResponseFactory implements OcrResponseFactory {
  private final String responseText;
  static Function<OcrCommand, OcrResponseFactory> newInstance(String responseText){
    return ocrCommand -> new SuccessOcrResponseFactory(responseText);
  }
  @Override
  public boolean isSuccessful() {
    return true;
  }

  @Override
  public int code() {
    return 200;
  }

  @Override
  public String message() {

    throw  new NotImplementedException("no way to invoke");
  }

  @Override
  public String bodyAsString() {
    return responseText;
  }

  @Override
  public void closeStream() {

  }
}
