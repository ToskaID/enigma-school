package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.model.Subject;
import com.example.EnigmaSchool.utils.IRandomStringGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectRepo implements IRepo<Subject>{

    @Autowired
    IRandomStringGenerate randomStringGenerate;
    private List<Subject> subjects = new ArrayList<>();
    @Override
    public Subject create(Subject subject) throws Exception {
        subject.setSubjectId(randomStringGenerate.random());
        subjects.add(subject);
        return subject;
    }

    @Override
    public List<Subject> getAll() throws Exception {
        return subjects;
    }

    @Override
    public Optional<Subject> getById(String id) throws Exception {
        for (Subject subject : subjects){
            if (subject.getSubjectId().equalsIgnoreCase(id)){
                return Optional.of(subject);
            }
        }
        return Optional.empty();
    }
}
