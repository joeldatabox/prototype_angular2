package br.com.prototype.controller.api.v1;

import br.com.prototype.model.Pessoa;
import br.com.prototype.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by master on 07/02/17.
 */
@RestController
@RequestMapping(value = "/api/v1/pessoas", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class PessoaController extends Controller {

    private PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        super(service);
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa record = service.save(pessoa);
        publishCreateResourceEvent(response, pessoa);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa record = service.update(pessoa);
        publishCreateResourceEvent(response, pessoa);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestParam Map<String, String> allRequestParams, HttpServletResponse response, UriComponentsBuilder componentsBuilder) {
        return ResponseEntity.ok(toPageableResource(allRequestParams, response, componentsBuilder));
    }
}
