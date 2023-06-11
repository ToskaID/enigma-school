package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.repository.IRepo;
import com.example.EnigmaSchool.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService implements IService<Student>{

    @Autowired
    StudentRepo studentRepo;
    @Override
    public Student create(Student student) {
        try{
            return studentRepo.create(student);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getAll() {
        try{
            return studentRepo.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> getById(String id) {
        try{
            return studentRepo.getById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
