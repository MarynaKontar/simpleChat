package com.app.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by User on 04.06.2017.
 */
public interface DAO<K extends Serializable, T> {

    void create(T entity);

   Optional<T> read(K key);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();
}
