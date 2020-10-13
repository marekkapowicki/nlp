package pl.marekk.nlp.textextraction.application.headers;

import lombok.AllArgsConstructor;
import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
class HeadersExtractionCommand {
  private final TextExtractionCommand textExtractionCommand;
  private final Map<String, String> additionalOcrHeaders;

  boolean ocrRequired() {
    return textExtractionCommand.ocrRequired();
  }

  List<Map.Entry<String, String>> additionalOcrHeaders() {
    return additionalOcrHeaders == null
            ? new ArrayList<>()
            : additionalOcrHeaders.keySet().stream()
            .map(key -> Map.entry(key, additionalOcrHeaders.get(key)))
            .collect(Collectors.toList());
  }

  public TextExtractionCommand textExtractionCommand() {
    return textExtractionCommand;
  }
}
