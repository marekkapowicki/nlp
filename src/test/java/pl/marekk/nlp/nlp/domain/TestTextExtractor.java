package pl.marekk.nlp.nlp.domain;

import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;
import pl.marekk.nlp.textextraction.domain.TextExtractionResponseFactory;
import pl.marekk.nlp.textextraction.domain.TextExtractor;

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
