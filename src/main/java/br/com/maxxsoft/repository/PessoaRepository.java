package br.com.maxxsoft.repository;

import br.com.maxxsoft.model.Pessoa;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 08/03/17.
 */
@Repository
public interface PessoaRepository extends MongoRepository<User, String>, PrototypeRepositoryCustom<User, String> {

    Pessoa findByCpf(String cpf);

}
