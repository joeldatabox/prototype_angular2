package br.com.maxxsoft.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypeNoContentException extends PrototypeException {

    public PrototypeNoContentException(Class clazz, String field, String message) {
        super("No Content", HttpStatus.NO_CONTENT, clazz, field, message);
    }
}
