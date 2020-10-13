package pl.marekk.nlp.textextraction.domain;

import com.neovisionaries.i18n.LanguageCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@UtilityClass
public class TextExtractionCommandBuilder {

  public static Optional<TextExtractionInput> buildTextExtractionCommand(
      Supplier<byte[]> contentSupplier,
      String contentType,
      String fileName,
      LanguageCode language) {
    try {
      return Optional.of(TextExtractionInput.of(contentSupplier.get(), contentType, language));
    } catch (IllegalStateException e) {
      log.warn("issue during reading {}: {}", fileName, e.getMessage());
      return Optional.empty();
    }
  }
}
