package cz.jiripinkas.jba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SimpleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SimpleNotFoundException(String message) {
        super(message);
    }

}
