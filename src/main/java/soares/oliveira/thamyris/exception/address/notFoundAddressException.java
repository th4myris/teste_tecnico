package soares.oliveira.thamyris.exception.address;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class notFoundAddressException extends RuntimeException {
    public notFoundAddressException() {
        super("Endereço não encontrado");
    }
}
