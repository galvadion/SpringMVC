package com.services;

import java.util.List;

public interface GenericService<E,K> {
    public void saveOrUpdate(E entity);
    public List<E> getAll();
    public E get(K id);
    public void remove(E entity);
}
