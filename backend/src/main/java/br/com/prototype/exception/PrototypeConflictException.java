package br.com.prototype.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypeConflictException extends PrototypeException {

    public PrototypeConflictException(Class clazz, String field, String message) {
        super("Conflict", HttpStatus.CONFLICT, clazz, field, message);
        /*this.message = "Conflict";
        this.status = HttpStatus.CONFLICT;
        this.objectName = clazz.getSimpleName().toLowerCase();
        this.details.put(this.objectName + "." + field, message);*/
    }
}
