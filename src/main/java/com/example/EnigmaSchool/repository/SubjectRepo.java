package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.model.Subject;
import com.example.EnigmaSchool.utils.IRandomStringGenerate;
import com.example.EnigmaSchool.utils.Key;
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

    @Override
    public void update(Subject subject, String id) throws Exception {
        for (Subject existSubject : subjects){
            if (existSubject.getSubjectId().equalsIgnoreCase(id)){
                existSubject.setName(subject.getName());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Subject subject : subjects){
            if (subject.getSubjectId().equalsIgnoreCase(id)){
                subjects.remove(subject);
                break;
            }
        }
    }

    @Override
    public Optional<List<Subject>> addBulk(List<Subject> subjectList) throws Exception {
        for (Subject subject : subjects){
            subject.setSubjectId(randomStringGenerate.random());
        }
        subjects.addAll(subjectList);
        return  Optional.of(subjectList);
    }

    @Override
    public Optional<List<Subject>> findBy(Key by, String value) throws Exception {
        List<Subject> subjectList = new ArrayList<>();
        for (Subject subject : subjects){
            switch (by){
                case name -> {
                    if (subject.getName().toLowerCase().contains(value)){
                        subjectList.add(subject);
                    }
                }
            }
        }
        return subjectList.isEmpty() ? Optional.empty() : Optional.of(subjectList);
    }


}
