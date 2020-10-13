package pl.marekk.nlp.textextraction.application.headers;

import lombok.AllArgsConstructor;
import pl.marekk.nlp.textextraction.domain.TextExtractionCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum TikaHeaders {
    LANGUAGE(command -> command.getLanguage() != null, command -> new LanguageHeadersExtractor().tikaHeaders(command) ),
    NO_OCR(command -> false, command -> new NoOcrHeadersExtractor().tikaHeaders(command)),
    OCR(command -> true, command -> new OcrHeadersExtractor().tikaHeaders(command));
    private final Predicate<TextExtractionCommand> applicable;
    private final Function<TextExtractionCommand,List<Map.Entry<String, String>>> extractor;

    public static Map<String, String> toTikaHeaders(TextExtractionCommand command){
        return Arrays.stream(values())
                .map(header -> header.process(command))
                .filter(list -> !list.isEmpty())
                .flatMap(List::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Map.Entry<String, String>> process(TextExtractionCommand command){
        return Optional.of(command)
                .filter(applicable)
                .map(extractor)
                .orElse(new ArrayList<>());
    }

}
