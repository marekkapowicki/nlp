package pl.marekk.nlp.ocr.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marekk.nlp.ocr.domain.TextExtractionCommand;
import pl.marekk.nlp.ocr.domain.TextExtractor;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api/actions")
@AllArgsConstructor
class OcrController {

  private final TextExtractor textExtractor;

  @PostMapping(value = "/ocr", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<Map<String, Object>> processOcr(@Valid RestOcrRequest request) {
    log.info("processing the ocr for {}", request);
    return request
        .buildTextExtractionCommand()
        .map(this::processOcr)
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  private ResponseEntity<Map<String, Object>> processOcr(TextExtractionCommand textExtractionCommand) {
    return textExtractor.extractText(textExtractionCommand)
        .fold(
            FailureResponseBuilder::buildFailureResponse,
            SuccessResponseBuilder::buildSuccessRestResponse);
  }
}
