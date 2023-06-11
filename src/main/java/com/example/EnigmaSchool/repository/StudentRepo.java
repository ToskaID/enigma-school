package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.utils.IRandomStringGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepo implements IRepo<Student>{

    @Autowired
    IRandomStringGenerate randomStringGenerate;

    private List<Student> students = new ArrayList<>();
    @Override
    public Student create(Student student) throws Exception {
        student.setStudentId(randomStringGenerate.random());
        students.add(student);
        return student;
    }

    @Override
    public List<Student> getAll() throws Exception {
        return students;
    }

    @Override
    public Optional<Student> getById(String id) throws Exception {
        for (Student student : students){
            if(student.getStudentId().equalsIgnoreCase(id)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }


}
