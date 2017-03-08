package br.com.maxxsoft.controller.hateoas.event;

import br.com.dreams.model.Model;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by master on 03/03/17.
 */
public class ResourceCreatedEvent extends ApplicationEvent {
    private final HttpServletResponse response;
    private final Model model;

    public ResourceCreatedEvent(Object source, final HttpServletResponse response, final Model model) {
        super(source);
        this.response = response;
        this.model = model;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Model getModel() {
        return model;
    }
}
