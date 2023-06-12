package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.exception.NotFoundException;
import com.example.EnigmaSchool.model.Subject;
import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.repository.SubjectRepo;
import com.example.EnigmaSchool.utils.Key;
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
            List<Subject> subjects = subjectRepo.getAll();
            int data = subjects.size();
            if (data < 25){
                return subjectRepo.create(subject);
            }else {
                throw new Exception("Data Sudah Penuh");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Subject> getAll() {
        try {
            List<Subject> subjects = subjectRepo.getAll();
            if (!subjects.isEmpty()){
                return subjectRepo.getAll();
            }else {
                throw new NotFoundException("Student is empty");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Subject> getById(String id) {
        try{
            Optional<Subject> find = subjectRepo.getById(id);
            if (!find.isEmpty()){
                return subjectRepo.getById(id);
            }else {
                throw new NotFoundException("Id not found");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Subject subject, String id) {
        try {
            getById(id);
            subjectRepo.update(subject,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try{
            getById(id);
            subjectRepo.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Subject>> addBulk(List<Subject> subjectList) {
        try {
            for (Subject subject: subjectList){
                Optional<List<Subject>> existingSubject = subjectRepo.findBy(Key.name, subject.getName());
                if (subject.getName().isEmpty()) {
                    throw new Exception("subjectName is required");
                } else if (existingSubject.isPresent()) {
                    throw new Exception("Subject already exist");
                }
            }
            List<Subject> subjects = subjectRepo.getAll();
            int count = subjects.size() + subjectList.size();
            if(count <= 9 ){
                return subjectRepo.addBulk(subjectList);
            } else {
                throw new Exception("Can't add subject");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Optional<List<Subject>> findBy(Key by, String value) {
        try {
            Optional<List<Subject>> find = subjectRepo.findBy(by, value);
            if(find.isEmpty()){
                throw new Exception("Data not found");
            }
            return subjectRepo.findBy(by, value);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
