package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.exception.NotFoundException;
import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.repository.TeacherRepo;
import com.example.EnigmaSchool.utils.Key;
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
            List<Teacher> teachers = teacherRepo.getAll();
            int data = teachers.size();
            if (data < 25){
                return teacherRepo.create(teacher);
            }else {
                throw new Exception("Data Sudah Penuh");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Teacher> getAll() {
        try{
            List<Teacher> teachers = teacherRepo.getAll();
            if (!teachers.isEmpty()){
                return teacherRepo.getAll();
            }else {
                throw new NotFoundException("Student is empty");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Teacher> getById(String id) {

        try {
            Optional<Teacher> find = teacherRepo.getById(id);
            if (!find.isEmpty()){
                return teacherRepo.getById(id);
            }else {
                throw new NotFoundException("Id not found");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Teacher teacher, String id) {
        try{
            getById(id);
            teacherRepo.update(teacher,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try{
            getById(id);
            teacherRepo.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Teacher>> addBulk(List<Teacher> teacherList) {
        try{
            for (Teacher teacher : teacherList){
                if (teacher.getFirstName().isEmpty() || teacher.getEmail().isEmpty()){
                    throw new Exception("firstname and email are required");
                }
            }
            List<Teacher> teachers = teacherRepo.getAll();
            int data = teachers.size() + teacherList.size();
            if (data <= 25){
                return teacherRepo.addBulk(teacherList);
            }else {
                throw new Exception("data sudah penuh");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Teacher>> findBy(Key by, String value) {
        try{
            Optional<List<Teacher>> find = teacherRepo.findBy(by, value);
            if (find.isEmpty()){
                throw new Exception("Data tidak ditemukan");
            }
            return teacherRepo.findBy(by,value);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
