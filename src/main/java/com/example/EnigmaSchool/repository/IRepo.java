package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.utils.Key;

import java.util.List;
import java.util.Optional;

public interface IRepo<T>{
    T create(T params) throws Exception;
    List<T> getAll() throws Exception;
    Optional<T> getById(String id) throws Exception;
    void update(T params, String id) throws  Exception;

    void delete(String id) throws Exception;

    Optional<List<T>> addBulk(List<T> params) throws Exception;
    Optional<List<T>> findBy(Key by, String value) throws Exception;

}
