package pl.marekk.nlp.ocr.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import pl.marekk.nlp.ocr.domain.OcrSuccessResponse;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestOcrResponse {
    String text;

    static RestOcrResponse success(OcrSuccessResponse response) {
        return new RestOcrResponse(response.text());
    }
}
