package pl.marekk.nlp.textextraction.application.headers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OcrHeadersExtractor implements HeadersExtractor {

  @Override
  public List<Map.Entry<String, String>> tikaHeaders(HeadersExtractionCommand command) {
      List<Map.Entry<String, String>> ocrEntries =
              List.of(
                      Map.entry(X_TIKA_PDFEXTRACT_INLINE_IMAGES, "true"),
                      Map.entry(X_TIKA_PDFOCR_STRATEGY, "OCR_ONLY"));
      return Stream.of(ocrEntries, command.additionalOcrHeaders())
              .flatMap(List::stream)
              .collect(Collectors.toList());
  }
}
