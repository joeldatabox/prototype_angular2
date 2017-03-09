package br.com.maxxsoft.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypePageableRequestException extends PrototypeException {

    public PrototypePageableRequestException() {
        this("invalid params of pagination");
    }

    public PrototypePageableRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
