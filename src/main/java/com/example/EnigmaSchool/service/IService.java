package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.utils.Key;

import java.util.List;
import java.util.Optional;

public interface IService<T>{

    T create(T params);
    List<T> getAll();
    Optional<T> getById(String id);
    void update(T params, String id);
    void delete(String id) ;
    Optional<List<T>> addBulk(List<T> params);
    Optional<List<T>> findBy(Key by, String value);

}
