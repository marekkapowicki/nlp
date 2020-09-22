package pl.marekk.nlp.nlp.domain;

import pl.marekk.nlp.ocr.domain.Ocr;
import pl.marekk.nlp.ocr.domain.OcrCommand;
import pl.marekk.nlp.ocr.domain.OcrResponseFactory;

import java.util.function.Function;

public class TestOcr extends Ocr {
    public static TestOcr success(String expectedResponseText){
        return new TestOcr(SuccessOcrResponseFactory.newInstance(expectedResponseText));
    }
    public TestOcr(Function<OcrCommand, OcrResponseFactory> ocrFunction) {
        super(ocrFunction);
    }
}
