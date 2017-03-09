package br.com.maxxsoft.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by joel on 19/01/17.
 */
public interface Model<T extends Serializable> {
    T getId();

    void setId(T id);

    default String toJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    default ResourceSupport toResource() {
        return new ModelResource<Model>(this);
    }
}
