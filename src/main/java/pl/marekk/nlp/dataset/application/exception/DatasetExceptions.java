package pl.marekk.nlp.dataset.application.exception;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

@UtilityClass
public final class DatasetExceptions {

    public static Supplier<RuntimeException> notFound(String message) {
        return () -> new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }
}
