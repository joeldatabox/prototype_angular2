package br.com.maxxsoft.controller.api.v1;

import br.com.maxxsoft.controller.hateoas.event.PaginatedResultsRetrievedEvent;
import br.com.maxxsoft.controller.hateoas.event.ResourceCreatedEvent;
import br.com.maxxsoft.controller.hateoas.event.SingleResourceRetrievedEvent;
import br.com.maxxsoft.controller.hateoas.resource.MetadataPageable;
import br.com.maxxsoft.controller.hateoas.resource.PageableResource;
import br.com.maxxsoft.exception.PrototypeBadRequestException;
import br.com.maxxsoft.exception.PrototypePageableRequestException;
import br.com.maxxsoft.model.Model;
import br.com.maxxsoft.repository.infra.Operators;
import br.com.maxxsoft.repository.infra.QueryBuilder;
import br.com.maxxsoft.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by joel on 19/01/17.
 */
public class Controller<T extends Serializable> {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    protected Service<Model> service;

    public Controller(Service service) {
        this.service = service;
    }

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

    PageableResource toPageableResource(final Map<String, String> allRequestParams, final HttpServletResponse response, final UriComponentsBuilder componentsBuilder) {
        validPageable(allRequestParams);
        Long page = Long.valueOf(allRequestParams.get(Operators.PAGE.toString()));
        Long pageSize = Long.valueOf(allRequestParams.get(Operators.LIMIT.toString()));

        Long total = service.count(allRequestParams);
        List<Model> records = service.findAll(allRequestParams);


        Double totalPages = 0D;
        if (total > 0L && total <= 100L) {
            totalPages++;
        } else {
            totalPages = Math.floor(total / pageSize);
            if (total % pageSize != 0.0) {
                totalPages++;
            }
        }
        publishPaginatedResultsRetrievedEvent(response, componentsBuilder, page, pageSize, totalPages.longValue(), Long.valueOf(records.size()));
        PageableResource p = new PageableResource(records, new MetadataPageable(page, pageSize, totalPages.longValue(), Long.valueOf(records.size())));
        return p;
    }

    private void validPageable(final Map<String, String> allRequestParams) {
        PrototypePageableRequestException ex = new PrototypePageableRequestException();

        if (allRequestParams.containsKey(Operators.LIMIT.toString())) {
            Integer limit = null;
            try {
                limit = Integer.valueOf(allRequestParams.get(Operators.LIMIT.toString()));
                if (limit > 100) {
                    ex.addDetails(Operators.LIMIT.toString(), "o limite informado foi (" + limit + ") mas o maxímo é(100)");
                }
            } catch (NumberFormatException nex) {
                ex.addDetails(Operators.LIMIT.toString(), "deve conter um numero com o tamanho maximo de 100");
            }
        } else {
            allRequestParams.put(Operators.LIMIT.toString(), "100");
        }

        if (allRequestParams.containsKey(Operators.PAGE.toString())) {
            Integer page = null;
            try {
                page = Integer.valueOf(allRequestParams.get(Operators.PAGE.toString()));
                if (page < 0) {
                    ex.addDetails(Operators.PAGE.toString(), "a pagina informada foi (" + page + ") mas a deve ter o tamanho minimo de (0)");
                }
            } catch (NumberFormatException nex) {
                ex.addDetails(Operators.PAGE.toString(), "deve conter um numero com o tamanho minimo de 0");
            }
        } else {
            allRequestParams.put(Operators.PAGE.toString(), "0");
        }

        if (ex.containsDetais()) {
            throw ex;
        }
    }
}
