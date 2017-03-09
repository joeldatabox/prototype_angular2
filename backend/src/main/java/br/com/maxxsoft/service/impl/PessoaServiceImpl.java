package br.com.maxxsoft.service.impl;

import br.com.maxxsoft.exception.PrototypeConflictException;
import br.com.maxxsoft.exception.PrototypeNoContentException;
import br.com.maxxsoft.exception.PrototypeNotFoundException;
import br.com.maxxsoft.model.Pessoa;
import br.com.maxxsoft.repository.PessoaRepository;
import br.com.maxxsoft.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by master on 08/03/17.
 */
@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return merge(pessoa);
    }

    @Override
    public boolean remove(Pessoa pessoa) {
        repository.delete(pessoa);
        return true;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return merge(pessoa);
    }

    @Override
    public Pessoa findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    @Override
    public Pessoa findByCpf(String cpf) {
        Pessoa pessoa = repository.findByCpf(cpf);
        if (pessoa == null) {
            throw new PrototypeNotFoundException(Pessoa.class, "cpf", "registro não encontrado");
        }
        return pessoa;
    }

    private Pessoa merge(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            //verificando se o cpf já existe
            try {
                findByCpf(pessoa.getCpf());
                throw new PrototypeConflictException(Pessoa.class, "cpf", "já existe um registro com esse CPF \"" + pessoa.getCpf() + "\'");
            } catch (PrototypeNotFoundException ex) {
            }
            return repository.save(pessoa);
        } else {
            return repository.save(pessoa);
        }
    }

    @Override
    public Long count(Map<String, String> allRequestParams) {
        return repository.count(allRequestParams);
    }

    @Override
    public List<Pessoa> findAll(Map<String, String> allRequestParams) {
        List<Pessoa> records = repository.findAll(allRequestParams);
        if (records == null || records.isEmpty()) throw new PrototypeNoContentException(Pessoa.class, null, "records notfound");
        return repository.findAll(allRequestParams);
    }
}
