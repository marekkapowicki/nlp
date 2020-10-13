package pl.marekk.nlp.textextraction.application.headers;

import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;

import java.util.List;
import java.util.Map;

class NoOcrHeadersExtractor implements HeadersExtractor {
  @Override
  public List<Map.Entry<String, String>> tikaHeaders(TextExtractionCommand command) {
    return List.of(
        Map.entry("X-Tika-PDFextractInlineImages", "false"),
        Map.entry("X-Tika-PDFocrStrategy", "NO_OCR"));
  }
}
