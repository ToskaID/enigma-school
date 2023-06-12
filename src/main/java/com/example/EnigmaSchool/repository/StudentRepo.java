package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.utils.IRandomStringGenerate;
import com.example.EnigmaSchool.utils.Key;
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

    @Override
    public void update(Student student, String id) throws Exception {
        for (Student existStudent : students){
            if (existStudent.getStudentId().equalsIgnoreCase(id)){
                existStudent.setFirstName(student.getFirstName());
                existStudent.setLastName(student.getLastName());
                existStudent.setEmail(student.getEmail());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Student student : students){
            if (student.getStudentId().equalsIgnoreCase(id)){
                students.remove(student);
                break;
            }
        }
    }

    @Override
    public Optional<List<Student>> addBulk(List<Student> studentList) throws Exception {
        for (Student student : students){
            student.setStudentId(randomStringGenerate.random());
        }
        students.addAll(studentList);
        return Optional.of(studentList);
    }

    @Override
    public Optional<List<Student>> findBy(Key by, String value) throws Exception {
        List<Student> studentList = new ArrayList<>();
        for (Student student : students){
            switch (by){
                case firstName -> {
                    if (student.getFirstName().toLowerCase().contains(value)){
                        studentList.add(student);
                    }
                }
                case lastName -> {
                    if (student.getLastName().toLowerCase().contains(value)){
                        studentList.add(student);
                    }
                }

                case email -> {
                    if (student.getEmail().toLowerCase().contains(value)){
                        studentList.add(student);
                    }
                }

            }
        }
        return studentList.isEmpty() ? Optional.empty() : Optional.of(studentList);
    }


}
