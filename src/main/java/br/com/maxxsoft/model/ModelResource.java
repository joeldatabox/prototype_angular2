package br.com.maxxsoft.model;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by master on 09/03/17.
 */
public class ModelResource<T extends Model> extends ResourceSupport {
    private T model;

    public ModelResource(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }
}
