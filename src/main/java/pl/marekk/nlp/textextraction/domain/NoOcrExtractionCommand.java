package pl.marekk.nlp.textextraction.domain;

import com.neovisionaries.i18n.LanguageCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "content")
class NoOcrExtractionCommand implements TextExtractionCommand {
  byte[] content;
  String contentType;

  @Override
  public boolean ocrRequired() {
    return false;
  }

  @Override
  public LanguageCode getLanguage() {
    return null;
  }
}
