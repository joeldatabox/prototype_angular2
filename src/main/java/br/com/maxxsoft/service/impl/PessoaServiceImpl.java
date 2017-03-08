package br.com.maxxsoft.service.impl;

import br.com.maxxsoft.model.Pessoa;
import br.com.maxxsoft.repository.PessoaRepository;
import br.com.maxxsoft.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by master on 08/03/17.
 */
@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository repository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return null;
    }

    @Override
    public boolean remove(Pessoa pessoa) {
        return false;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return null;
    }

    @Override
    public Pessoa findById(String id) {
        return null;
    }

    @Override
    public List<Pessoa> findAll() {
        return null;
    }

    @Override
    public Pessoa findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    private Pessoa merge(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            //verificando se a conta já existe
            Pessoa other = null;
            try {
                other = findByDescricao(pessoa.getDescricao(), pessoa.getUser());
                throw new DreamsConflictException(Conta.class, "descricao", "já existe um registro com essa descrição \"" + pessoa.getDescricao() + "\'");
            } catch (DreamsNotFoundException ex) {
            }
            return repository.save(pessoa);
        } else {
            Conta other = findById(pessoa.getId());
            //não pode-se alterar o usuário
            if (!other.equals(pessoa)) {
                throw new DreamsBadRequestException(Conta.class, "user", "não é possível fazer a alteração do usuário dono da conta");
            }
            //verificando se o nome alterado já existe
            try {
                other = findByDescricao(pessoa.getDescricao(), pessoa.getUser());
                throw new DreamsConflictException(Conta.class, "descricao", "já existe um registro com essa descrição \"" + pessoa.getDescricao() + "\'");
            } catch (DreamsNotFoundException ex) {
            }
            return repository.save(pessoa);
        }
    }
}
