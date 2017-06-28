package com.app.DAO;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

/**
 * Created by User on 04.06.2017.
 */
public interface DAO<K extends Serializable, T> {

    void create(T entity);

   Optional<T> read(K key);

    void update(T entity);

    void delete(T entity);

    Set<T> getAll();
}
