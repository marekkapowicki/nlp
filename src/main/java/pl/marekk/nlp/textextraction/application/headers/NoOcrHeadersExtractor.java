package pl.marekk.nlp.textextraction.application.headers;

import java.util.List;
import java.util.Map;

class NoOcrHeadersExtractor implements HeadersExtractor {
  @Override
  public List<Map.Entry<String, String>> tikaHeaders(HeadersExtractionCommand command) {
    return List.of(
            Map.entry(X_TIKA_PDFEXTRACT_INLINE_IMAGES, "false"),
            Map.entry(X_TIKA_PDFOCR_STRATEGY, "NO_OCR"));
  }
}
