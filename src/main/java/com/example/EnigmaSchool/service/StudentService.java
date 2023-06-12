package com.example.EnigmaSchool.service;

import com.example.EnigmaSchool.exception.NotFoundException;
import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.repository.StudentRepo;
import com.example.EnigmaSchool.utils.Key;
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
            List<Student> students = studentRepo.getAll();
            int data = students.size();
            if (data < 25){
                return studentRepo.create(student);
            }else {
                throw new Exception("Data Sudah Penuh");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Student> getAll() {
        try{
            List<Student> students = studentRepo.getAll();
            if (!students.isEmpty()){
                return studentRepo.getAll();
            }else {
                throw new NotFoundException("Student is empty");
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Student> getById(String id) {
        try{
            Optional<Student> find = studentRepo.getById(id);
            if (!find.isEmpty()){
                return studentRepo.getById(id);
            }else {
                throw new NotFoundException("Id not found");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Student student, String id) {
        try{
            getById(id);
            studentRepo.update(student,id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            getById(id);
            studentRepo.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Student>> addBulk(List<Student> studentList) {
        try{
            for (Student student : studentList){
                if (student.getFirstName().isEmpty() || student.getEmail().isEmpty()){
                    throw new Exception("firstname and email are required");
                }
            }
            List<Student> students = studentRepo.getAll();
            int data = students.size() + studentList.size();
            if (data <= 25){
                return studentRepo.addBulk(studentList);
            }else {
                throw new Exception("data sudah penuh");
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<List<Student>> findBy(Key by, String value) {
        try{
            Optional<List<Student>> find = studentRepo.findBy(by, value);
            if (find.isEmpty()){
                throw new Exception("Data tidak ditemukan");
            }
            return studentRepo.findBy(by,value);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
