package soares.oliveira.thamyris.exception.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundPersonException extends RuntimeException {
    public NotFoundPersonException() {
        super("Pessoa não encontrada");
    }
}
