package br.com.maxxsoft.controller.api.v1;

import br.com.dreams.controller.hateoas.resource.ContaResource;
import br.com.dreams.controller.hateoas.resource.PageableResource;
import br.com.dreams.model.Conta;
import br.com.dreams.repository.ContaRepository;
import br.com.dreams.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by master on 07/02/17.
 */
@RestController
@RequestMapping(value = "/api/v1/{id_user}/contas", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ContaController extends Controller {

    @Autowired
    private ContaService service;
    @Autowired
    private ContaRepository repository;
    //@Autowired
    //private Test repo;


    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Conta conta, HttpServletResponse response) {
        Conta record = service.save(conta);
        publishCreateResourceEvent(response, conta);
    }

    /**
     * Ativa uma conta
     *
     * @param id -> id da conta
     */
    @RequestMapping(value = "/{id}/enable", method = RequestMethod.PUT)
    public ResponseEntity enable(@PathVariable String id) {
        service.enable(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Ativa uma conta
     *
     * @param id -> id da conta
     */
    @RequestMapping(value = "/{id}/disable", method = RequestMethod.PUT)
    public ResponseEntity disable(@PathVariable String id) {
        service.disable(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam Map<String, String> allRequestParams, HttpServletResponse response, UriComponentsBuilder componentsBuilder) {

        allRequestParams.containsKey("extends");
        //List l = repo.getList(allRequestParams);
        List l =repository.findAll(allRequestParams);
        HttpServletRequest r = null;
        List list = service.findAll().stream().map(ContaResource::new).collect(Collectors.toList());
        publishPaginatedResultsRetrievedEvent(response, componentsBuilder, 10L, 11L, 12L, 13L);
        return ResponseEntity.ok(new PageableResource(list, null));
    }
}
