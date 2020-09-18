package pl.marekk.nlp.ocr.api;

import com.neovisionaries.i18n.LanguageCode;
import io.vavr.API;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import pl.marekk.nlp.ocr.domain.OcrCommand;
import pl.marekk.nlp.ocr.domain.OcrCommandBuilder;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.springframework.util.StreamUtils.copyToByteArray;

@Slf4j
@AllArgsConstructor
@Data
public class RestOcrRequest {
  @NotNull(message = "file to upload is null")
  private MultipartFile fileToOcr;
  private LanguageCode language;

  Optional<OcrCommand> buildOcrCommand() {
    return OcrCommandBuilder.buildOcrCommand(
            this::contentAsByteArray,
            fileToOcr.getContentType(),
            fileToOcr.getOriginalFilename(),
            language);
  }

  private byte[] contentAsByteArray() {
    return Optional.of(fileToOcr)
            .map(API.unchecked(file -> copyToByteArray(file.getInputStream())))
            .orElseThrow(() -> new IllegalStateException("issue during reading the fileToOcr"));
  }

  @Override
  public String toString() {
    return "RestOcrRequest{"
            + "language: "
            + language
            + ", fileToOcr="
            + fileToOcr.getOriginalFilename()
            + '}';
  }
}
