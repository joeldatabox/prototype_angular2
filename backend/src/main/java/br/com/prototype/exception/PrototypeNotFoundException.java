package br.com.prototype.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypeNotFoundException extends PrototypeException {

    public PrototypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public PrototypeNotFoundException(Class clazz, String field, String message) {
        super("Resource Not Found", HttpStatus.NOT_FOUND, clazz, field, message);
    }
}
