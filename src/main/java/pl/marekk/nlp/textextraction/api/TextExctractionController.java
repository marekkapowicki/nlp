package pl.marekk.nlp.textextraction.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marekk.nlp.textextraction.domain.TextExtractionInput;
import pl.marekk.nlp.textextraction.domain.TextExtractor;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api/actions")
@AllArgsConstructor
class TextExctractionController {

  private final TextExtractor textExtractor;

  @PostMapping(value = "/extractText", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  ResponseEntity<Map<String, Object>> processOcr(@Valid RestTextExtractionRequest request) {
    log.info("processing the ocr for {}", request);
    return request
        .buildTextExtractionCommand()
        .map(this::processOcr)
        .orElseGet(() -> ResponseEntity.badRequest().build());
  }

  private ResponseEntity<Map<String, Object>> processOcr(TextExtractionInput textExtractionInput) {
    return textExtractor
        .extractText(textExtractionInput)
        .fold(
            FailureResponseBuilder::buildFailureResponse,
            SuccessResponseBuilder::buildSuccessRestResponse);
  }
}
