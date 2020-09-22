package pl.marekk.nlp.ocr.application;

import com.neovisionaries.i18n.LanguageCode;
import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import pl.marekk.nlp.ocr.domain.OcrCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static pl.marekk.nlp.ocr.application.TikaHeaders.TikaLanguageHeader.tikaLanguageHeader;

@UtilityClass
class TikaHeaders {
  static Map<String, String> tikaHeaders(OcrCommand command, Map<String, String> headers) {
    Optional<Map.Entry<String, String>> languageEntry = tikaLanguageHeader(command);
    return languageEntry.map(entry -> addToMap(headers, entry)).orElseGet(() -> headers);
  }

  private static Map<String, String> addToMap(
      Map<String, String> map, Map.Entry<String, String> entry) {
    if (entry == null) {
      return map;
    }
    if (map == null || map.isEmpty()) {
      return Map.ofEntries(entry);
    }
    Map<String, String> resultMap = new HashMap<>(map);
    resultMap.put(entry.getKey(), entry.getValue());
    return resultMap;
  }

  @UtilityClass
  static class TikaLanguageHeader {
    static Optional<Map.Entry<String, String>> tikaLanguageHeader(OcrCommand ocrCommand) {
      return tikaLanguageHeader(ocrCommand.getLanguage());
    }

    private static Optional<Map.Entry<String, String>> tikaLanguageHeader(
        LanguageCode languageCode) {
      return Optional.ofNullable(languageCode)
          .flatMap(TikaLanguage::of)
          .map(TikaLanguage::tikaHeader);
    }

    @AllArgsConstructor
    private enum TikaLanguage {
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
}
