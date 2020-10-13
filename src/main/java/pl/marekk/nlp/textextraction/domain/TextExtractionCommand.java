package pl.marekk.nlp.textextraction.domain;

import com.neovisionaries.i18n.LanguageCode;

public interface TextExtractionCommand {
  static TextExtractionCommand ocrCommand(TextExtractionInput textExtractionInput) {
    return new OcrExtractionCommand(
        textExtractionInput.getContent(),
        textExtractionInput.getContentType(),
        textExtractionInput.getLanguage());
  }

  static TextExtractionCommand noOcrCommand(TextExtractionInput textExtractionInput) {
    return new NoOcrExtractionCommand(
        textExtractionInput.getContent(), textExtractionInput.getContentType());
  }

  boolean ocrRequired();

  byte[] getContent();

  LanguageCode getLanguage();
}
