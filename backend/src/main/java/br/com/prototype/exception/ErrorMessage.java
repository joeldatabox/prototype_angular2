package br.com.prototype.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joel on 30/01/17.
 */
public class ErrorMessage {
    protected HttpStatus status;
    protected String message;
    protected String objectName;
    protected Map<String, Object> details;

    public ErrorMessage() {
        this.details = new HashMap<>();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getObjectName() {
        return objectName;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    @JsonIgnore
    protected String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonIgnore
    protected ResponseEntity<ErrorMessage> toResponseEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        return new ResponseEntity(this, headers, this.status);
    }
}
