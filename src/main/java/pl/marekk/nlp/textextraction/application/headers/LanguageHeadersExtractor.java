package pl.marekk.nlp.textextraction.application.headers;

import com.neovisionaries.i18n.LanguageCode;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class LanguageHeadersExtractor implements HeadersExtractor {
  @Override
  public List<Map.Entry<String, String>> tikaHeaders(HeadersExtractionCommand command) {
    return TikaLanguage.of(command.textExtractionCommand().getLanguage())
            .map(TikaLanguage::tikaHeader)
            .map(List::of)
            .orElse(new ArrayList<>());
  }

  @AllArgsConstructor
  enum TikaLanguage {
    german(LanguageCode.de, "deu"),
    english(LanguageCode.en, "eng"),
    polish(LanguageCode.pl, "pol");

    private static final String tikaLanguageHeader = "X-Tika-OCRLanguage";
    private final LanguageCode languageCode;
    /** @see <a href="https://github.com/tesseract-ocr/tessdata">tesseract languages</a> */
    private final String tesseractLanguageValue;

    static Optional<TikaLanguage> of(LanguageCode languageCode) {
      return Arrays.stream(TikaLanguage.values())
          .filter(tikaLanguage -> tikaLanguage.languageCode == languageCode)
          .findFirst();
    }

    Map.Entry<String, String> tikaHeader() {
      return Map.entry(tikaLanguageHeader, tesseractLanguageValue);
    }
  }
}
