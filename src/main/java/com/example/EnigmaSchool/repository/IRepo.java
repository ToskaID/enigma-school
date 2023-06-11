package com.example.EnigmaSchool.repository;

import java.util.List;
import java.util.Optional;

public interface IRepo<T>{
    T create(T params) throws Exception;
    List<T> getAll() throws Exception;
    Optional<T> getById(String id) throws Exception;
}
