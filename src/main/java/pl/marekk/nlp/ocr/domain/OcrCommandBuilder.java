package pl.marekk.nlp.ocr.domain;

import com.neovisionaries.i18n.LanguageCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@UtilityClass
public class OcrCommandBuilder {

  public static Optional<OcrCommand> buildOcrCommand(
      Supplier<byte[]> contentSupplier,
      String contentType,
      String fileName,
      LanguageCode language) {
    try {
      return Optional.of(OcrCommand.of(contentSupplier.get(), contentType, language));
    } catch (IllegalStateException e) {
      log.warn("issue during reading {}: {}", fileName, e.getMessage());
      return Optional.empty();
    }
  }
}
