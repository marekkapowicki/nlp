package pl.marekk.nlp.textextraction.application.headers;

import java.util.List;
import java.util.Map;

@FunctionalInterface
interface HeadersExtractor {
  String X_TIKA_PDFEXTRACT_INLINE_IMAGES = "X-Tika-PDFextractInlineImages";
  String X_TIKA_PDFOCR_STRATEGY = "X-Tika-PDFocrStrategy";

  List<Map.Entry<String, String>> tikaHeaders(HeadersExtractionCommand command);
}
