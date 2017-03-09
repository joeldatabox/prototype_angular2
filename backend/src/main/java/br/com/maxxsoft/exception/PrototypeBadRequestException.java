package br.com.maxxsoft.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypeBadRequestException extends PrototypeException {

    public PrototypeBadRequestException(Class clazz, String field, String message) {
        super("Bad Request", HttpStatus.BAD_REQUEST, clazz, field, message);
    }
}
