package br.com.prototype.service;

import br.com.prototype.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by master on 09/03/17.
 */
public interface Service<T extends Model> {
    Long count(Map<String, String> allRequestParams);

    List<T> findAll(Map<String, String> allRequestParams);
}
