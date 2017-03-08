package br.com.maxxsoft.model;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by joel on 19/01/17.
 */
public interface Model<T> {
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
}
