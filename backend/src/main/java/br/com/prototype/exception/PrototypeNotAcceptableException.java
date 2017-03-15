package br.com.prototype.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypeNotAcceptableException extends PrototypeException {

    public PrototypeNotAcceptableException(Class clazz, String field, String message) {
        super("Not Acceptable", HttpStatus.NOT_ACCEPTABLE, clazz, field, message);
    }
}
