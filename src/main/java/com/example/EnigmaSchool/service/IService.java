package com.example.EnigmaSchool.service;

import java.util.List;
import java.util.Optional;

public interface IService<T>{

    T create(T params);
    List<T> getAll();
    Optional<T> getById(String id);
}
