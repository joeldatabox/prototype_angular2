package br.com.prototype.exception;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by joel on 20/01/17.
 */
@Component
public class ErrorMessageBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ErrorMessageBuilder.class);

    private ErrorMessage message;

    @Value("${dreams.print.stackTrace:false}")
    private boolean STACK_TRACE;

    @Value("${dreams.print.responseException:false}")
    private boolean RESPONSE_EXCEPTION;

    public ErrorMessage build(MethodArgumentNotValidException ex) {
        this.message = new ErrorMessage();
        setStatus(HttpStatus.BAD_REQUEST);
        setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        setObjectName(ex.getBindingResult().getObjectName());
        for (ObjectError fieldError : ex.getBindingResult().getAllErrors()) {
            String key = fieldError.getCodes()[0].replace(fieldError.getCodes()[fieldError.getCodes().length - 1] + ".", "");
            addDetails(key, fieldError.getDefaultMessage());
        }
        printException(ex);
        return this.message;
    }

    public ErrorMessage build(HttpMediaTypeNotSupportedException ex) {
        this.message = new ErrorMessage();
        setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        setMessage(ex.getMessage().replace("'null' ", ""));
        String contentType = "uninformed";
        addDetails("ContentType", ex.getContentType() == null ? contentType : ex.getContentType().toString());
        addDetails("SupportedMediaTypes", ex.getSupportedMediaTypes().stream().map(mt -> mt.toString()).collect(Collectors.toList()));
        printException(ex);
        return this.message;
    }

    public ErrorMessage build(HttpMessageNotReadableException ex) {
        this.message = new ErrorMessage();
        setStatus(HttpStatus.BAD_REQUEST);
        //procurando exceções de negocio
        PrototypeException de = (PrototypeException) findDreamsException(ex, null);

        if (de != null) {
            return build(de);
        } else if (ex.getCause() instanceof com.fasterxml.jackson.core.JsonParseException) {
            JsonLocation location = ((com.fasterxml.jackson.core.JsonParseException) ex.getCause()).getLocation();
            setMessage("Illegal character in line:" + location.getLineNr() + " column:" + location.getColumnNr());
        } else if (ex.getCause() instanceof JsonMappingException) {
            JsonLocation location = ((JsonMappingException) ex.getCause()).getLocation();
            if (location != null) {
                setMessage("Illegal character in line:" + location.getLineNr() + " column:" + location.getColumnNr());
            }
        } else if (ex.getMessage().contains("Required request body is missing:")) {
            setMessage("Insira o corpo na requisição!");
            addDetails("body", "body is empty");
        }
        printException(ex);
        return this.message;
    }

    public ErrorMessage build(PrototypeException ex) {
        this.message = new ErrorMessage();
        setStatus(ex.status);
        setMessage(ex.message);
        setObjectName(ex.objectName);
        addDetails(ex.details);
        printException(ex);
        return this.message;
    }

    public ErrorMessageBuilder setStatus(HttpStatus status) {
        this.message.status = status;
        return this;
    }

    public ErrorMessageBuilder setStatus(Integer status) {
        this.message.status = HttpStatus.valueOf(status);
        return this;
    }

    public ErrorMessageBuilder setObjectName(String objectName) {
        this.message.objectName = objectName;
        return this;
    }

    public ErrorMessageBuilder setObjectName(Object objectName) {
        this.message.objectName = objectName.getClass().getSimpleName();
        return this;
    }

    public ErrorMessageBuilder setMessage(String message) {
        this.message.message = message;
        return this;
    }

    public ErrorMessageBuilder addDetails(String key, Object value) {
        this.message.details.put(key, value);
        return this;
    }

    public ErrorMessageBuilder addDetails(String key, List<Object> value) {
        this.message.details.put(key, value);
        return this;
    }

    public ErrorMessageBuilder addDetails(Map<String, Object> details) {
        this.message.details.putAll(details);
        return this;
    }

    /**
     * Imprime a pilha de Exceptions de acordo com application.properties
     *
     * @param ex ->exceção para ser logada!
     */
    private void printException(Exception ex) {
        if (STACK_TRACE) {
            ex.printStackTrace();
        }
        if (RESPONSE_EXCEPTION) {
            logger.info(this.message.toJson());
        }
    }

    /**
     * Verifica se na arvore de exceptions existe algum erro advindo de regra de negocio.
     * Caso exista esse erro é retornado primordialmente;
     *
     * @param exActual  ->Exception atual
     * @param exPrevius ->Exception que aponta para Exception atual
     */
    private Throwable findDreamsException(Throwable exActual, Throwable exPrevius) {
        if (exActual == null || exActual.equals(exPrevius)) {
            return null;
        }
        if (exActual instanceof PrototypeException) {
            return exActual;
        } else {
            return findDreamsException(exActual.getCause(), exActual);
        }

    }
}
