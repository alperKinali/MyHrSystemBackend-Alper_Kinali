package com.example.humanressapp.service.abstracts;

import java.util.List;

public interface BaseEntityService<T> {
    void add(T entity);

    void update(T entity);

    void delete(Long id);

    List<T> getAll();

    T getById(Long id);
    // burada yapılacakbir şey yok silinebilinir

}
