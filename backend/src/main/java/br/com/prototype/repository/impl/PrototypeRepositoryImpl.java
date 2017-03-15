package br.com.prototype.repository.impl;


import br.com.prototype.model.Model;
import br.com.prototype.repository.PrototypeRepositoryCustom;
import br.com.prototype.repository.infra.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PrototypeRepositoryImpl<T extends Model, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements PrototypeRepositoryCustom<T, ID> {

    protected final MongoOperations operations;
    private final Class<T> CLASS;

    public PrototypeRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.CLASS = metadata.getJavaType();
        this.operations = mongoOperations;
    }

    @Override
    public List<T> findAll(Map<String, String> queryParams) {
        return operations.find(QueryBuilder.createQuery(queryParams), CLASS);
    }

    public Page<T> findAllPageable(Pageable pageable, Map<String, String> queryParams) {
        Page<T> p = null;
        //p.nextPageable()
        return null;
    }

    @Override
    public long count(Map<String, String> queryParams) {
        return operations.count(QueryBuilder.createQuery(queryParams), CLASS);
    }
}
