package br.com.maxxsoft.repository;

import br.com.maxxsoft.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 08/03/17.
 */
@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String>, PrototypeRepositoryCustom<Pessoa, String> {

    Pessoa findByCpf(String cpf);

}
