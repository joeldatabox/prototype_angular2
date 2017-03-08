package br.com.maxxsoft.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by master on 07/03/17.
 */
@NoRepositoryBean
public interface PrototypeRepositoryCustom<T, ID extends Serializable> extends MongoRepository<T, ID> {

    List<T> findAll(Map<String, String> queryParams);

    // long count(Map<String, String> queryParams);
}
