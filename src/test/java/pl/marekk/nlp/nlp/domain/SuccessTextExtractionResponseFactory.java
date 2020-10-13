package pl.marekk.nlp.nlp.domain;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;
import pl.marekk.nlp.textextraction.domain.TextExtractionResponseFactory;

import java.util.function.Function;

@AllArgsConstructor
class SuccessTextExtractionResponseFactory implements TextExtractionResponseFactory {
  private final String responseText;
  static Function<TextExtractionCommand, TextExtractionResponseFactory> newInstance(String responseText){
    return ocrCommand -> new SuccessTextExtractionResponseFactory(responseText);
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
