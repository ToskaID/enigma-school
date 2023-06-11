package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements IService<Teacher>{

    @Autowired
    TeacherRepo teacherRepo;
    @Override
    public Teacher create(Teacher teacher) {
        try{
            return teacherRepo.create(teacher);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Teacher> getAll() {
        try{
            return teacherRepo.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Teacher> getById(String id) {

        try {
            return teacherRepo.getById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
