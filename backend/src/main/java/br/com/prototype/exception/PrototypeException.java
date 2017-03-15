package br.com.prototype.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joel on 17/01/17.
 */
public class PrototypeException extends RuntimeException {
    protected HttpStatus status;
    protected String message;
    protected String objectName;
    protected Map<String, Object> details = new HashMap<>();

    public PrototypeException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "ERROR *-*";
        this.objectName = "unknow :(";
    }

    public PrototypeException(String message, HttpStatus status, Class clazz, String field, String info) {
        this.message = message;
        this.status = status;
        this.objectName = clazz.getSimpleName().toLowerCase();
        this.details.put(this.objectName + "." + field, info);
    }

    public PrototypeException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public PrototypeException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.objectName = "unknow :(";
        this.status = status;
    }


    public PrototypeException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.objectName = "unknow :(";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PrototypeException(Throwable cause) {
        super(cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "ERROR *-*";
        this.objectName = "unknow :(";
    }

    public PrototypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.objectName = "unknow :(";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public PrototypeException addDetails(String key, Object value) {
        this.details.put(key, value);
        return this;
    }

    public PrototypeException addDetails(String key, List<Object> value) {
        this.details.put(key, value);
        return this;
    }

    public PrototypeException addDetails(Map<String, Object> details) {
        this.details.putAll(details);
        return this;
    }

    public boolean containsDetais() {
        return !this.details.isEmpty();
    }
}
