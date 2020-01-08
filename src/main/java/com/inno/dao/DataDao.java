package com.inno.dao;

import java.util.Collection;
import java.util.Optional;

public interface DataDao<T> {

    Optional<T> get(T t);

    Collection<T> getAll();

    boolean add(T t);

    boolean update(T t);

    boolean delete(T t);

    void createTable();
}
