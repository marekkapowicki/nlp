package pl.marekk.nlp.textextraction.domain;

import com.neovisionaries.i18n.LanguageCode;
import lombok.ToString;
import lombok.Value;

import static org.springframework.util.StringUtils.isEmpty;

@Value
@ToString(exclude = "content")
public class TextExtractionCommand {
  byte[] content;
  String contentType;
  LanguageCode language;

  public static TextExtractionCommand of(byte[] content, String contentType, LanguageCode language) {
    if (content == null) {
      throw new IllegalStateException("missing parameter content");
    }
    if (isEmpty(contentType)) {
      throw new IllegalStateException("missing parameter contentType");
    }
    return new TextExtractionCommand(content, contentType, language);
  }

}
