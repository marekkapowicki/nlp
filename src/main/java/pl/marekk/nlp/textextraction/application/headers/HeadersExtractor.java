package pl.marekk.nlp.textextraction.application.headers;

import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;

import java.util.List;
import java.util.Map;

@FunctionalInterface
interface HeadersExtractor {
  List<Map.Entry<String, String>> tikaHeaders(TextExtractionCommand command);
}
