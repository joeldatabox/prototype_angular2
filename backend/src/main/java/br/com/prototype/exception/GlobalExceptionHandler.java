package br.com.prototype.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 18/01/17.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @Autowired
    private ErrorMessageBuilder errorMessageBuilder;

    @ExceptionHandler(value = PrototypeException.class)
    public ResponseEntity dreamsException(PrototypeException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResponseEntity httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception ex) {
        return errorMessageBuilder.build(new PrototypeException("ERROR *-*", ex)).toResponseEntity();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity httpMessageNotReadableException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity validationException(MethodArgumentNotValidException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }
}
