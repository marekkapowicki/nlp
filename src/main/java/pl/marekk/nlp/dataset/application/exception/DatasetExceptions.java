package pl.marekk.nlp.dataset.application.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

@UtilityClass
@Slf4j
public final class DatasetExceptions {

    public static Supplier<RuntimeException> notFound(String message) {
        return () -> {
            log.error(message);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        };
    }
}
