package pl.marekk.nlp.textextraction.domain;

import java.util.function.Function;

public class TestTextExtractor extends TextExtractor {
    public static TestTextExtractor failure(int expectedCode, String expectedMessage){
        return new TestTextExtractor(FailureTextExtractionResponseFactory.newInstance(expectedCode, expectedMessage));
    }
    public static TestTextExtractor success(String expectedResponseText){
        return new TestTextExtractor(SuccessTextExtractionResponseFactory.newInstance(expectedResponseText));
    }
    public TestTextExtractor(Function<TextExtractionCommand, TextExtractionResponseFactory> ocrFunction) {
        super(ocrFunction);
    }
}
