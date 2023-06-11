package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.model.Subject;
import com.example.EnigmaSchool.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService implements IService<Subject>{

    @Autowired
    SubjectRepo subjectRepo;
    @Override
    public Subject create(Subject subject) {
        try {
            return subjectRepo.create(subject);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subject> getAll() {
        try {
            return subjectRepo.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Subject> getById(String id) {
        try{
            return subjectRepo.getById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
