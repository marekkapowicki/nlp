package pl.marekk.nlp.ocr.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OcrSuccessResponse {
    private final String text;

    public static OcrSuccessResponse success(String text) {
        return new OcrSuccessResponse(text);
    }

    public String text() {
        return text;
    }
}
