package br.com.prototype.controller.hateoas.listener;


import br.com.prototype.controller.hateoas.event.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by master on 03/03/17.
 */
@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {
    @Override
    public void onApplicationEvent(ResourceCreatedEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("Event has null");
        }
        event.getResponse()
                .setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(event.getModel().getId())
                        .toUri().toASCIIString());
    }
}
