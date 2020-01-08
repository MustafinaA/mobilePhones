package com.inno.dao;

import java.util.Collection;

public interface DataDao<T> {

    T get(T t);

    Collection<T> getAll();

    boolean add(T t);

    boolean update(T t);

    boolean delete(T t);

    void createTable();
}
