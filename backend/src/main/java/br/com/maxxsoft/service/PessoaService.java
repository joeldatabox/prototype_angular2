package br.com.maxxsoft.service;

import br.com.maxxsoft.model.Pessoa;

import java.util.List;

public interface PessoaService extends Service<Pessoa> {

    Pessoa save(Pessoa pessoa);

    boolean remove(Pessoa pessoa);

    Pessoa update(Pessoa pessoa);

    Pessoa findById(String id);

    List<Pessoa> findAll();

    Pessoa findByCpf(String cpf);

}