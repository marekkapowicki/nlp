package pl.marekk.nlp.dataset.application.exception;

import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public final class DatasetExceptions {

    public static Supplier<DatasetException> notFound(String message) {
        return () -> new DatasetNotFoundException(message);
    }
}
