package br.com.maxxsoft.controller.api.v1;

import br.com.dreams.controller.hateoas.event.PaginatedResultsRetrievedEvent;
import br.com.dreams.controller.hateoas.event.ResourceCreatedEvent;
import br.com.dreams.controller.hateoas.event.SingleResourceRetrievedEvent;
import br.com.dreams.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by joel on 19/01/17.
 */
public class Controller<T extends Serializable> {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    protected void publishCreateResourceEvent(HttpServletResponse response, Model model) {
        this.eventPublisher.publishEvent(
                new ResourceCreatedEvent(this, response, model)
        );
    }

    protected void publishSingleResourceRetrievedEvent(HttpServletResponse response) {
        this.eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
    }

    protected void publishPaginatedResultsRetrievedEvent(HttpServletResponse response, UriComponentsBuilder uriComponentsBuilder, Long page, Long pageSize, Long totalpages, Long totalRecords) {
        this.eventPublisher.publishEvent(
                new PaginatedResultsRetrievedEvent<T>(this, uriComponentsBuilder, response, page, pageSize, totalpages, totalRecords)
        );
    }

    //public PageableResource getPageable(List list,)
}
