package pl.marekk.nlp.textextraction.domain;

import com.neovisionaries.i18n.LanguageCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(exclude = "content")
class OcrExtractionCommand implements TextExtractionCommand {
  byte[] content;
  String contentType;
  LanguageCode language;

  @Override
  public boolean ocrRequired() {
    return true;
  }
}
