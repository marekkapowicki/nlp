package pl.marekk.nlp.dataset.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "dataset not found")
class DatasetNotFoundException extends DatasetException {
    DatasetNotFoundException(String message) {
        super(message);
    }
}
