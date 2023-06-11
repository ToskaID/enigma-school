package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.utils.IRandomStringGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class TeacherRepo implements IRepo<Teacher>{

    @Autowired
    IRandomStringGenerate randomStringGenerate;
    private List<Teacher> teachers = new ArrayList<>();
    @Override
    public Teacher create(Teacher teacher) throws Exception {
        teacher.setTeacherId(randomStringGenerate.random());
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public List<Teacher> getAll() throws Exception {
        return teachers;
    }

    @Override
    public Optional<Teacher> getById(String id) throws Exception {
        for (Teacher teacher : teachers){
            if (teacher.getTeacherId().equalsIgnoreCase(id)){
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }
}
