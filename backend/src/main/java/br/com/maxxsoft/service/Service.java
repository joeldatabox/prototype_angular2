package br.com.maxxsoft.service;

import br.com.maxxsoft.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by master on 09/03/17.
 */
public interface Service<T extends Model> {
    Long count(Map<String, String> allRequestParams);

    List<T> findAll(Map<String, String> allRequestParams);
}
