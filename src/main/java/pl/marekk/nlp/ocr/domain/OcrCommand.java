package pl.marekk.nlp.ocr.domain;

import lombok.Value;

import static org.springframework.util.StringUtils.isEmpty;

@Value
public class OcrCommand {
    byte[] content;
    String contentType;

    public static OcrCommand of(byte[] content, String contentType) {
        if (content == null) {
            throw new IllegalStateException("missing parameter content");
        }
        if (isEmpty(contentType)) {
            throw new IllegalStateException("missing parameter contentType");
        }
        return new OcrCommand(content, contentType);
    }

}
