package com.example.EnigmaSchool.repository;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.utils.IRandomStringGenerate;
import com.example.EnigmaSchool.utils.Key;
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

    @Override
    public void update(Teacher teacher, String id) throws Exception {
        for (Teacher existTeacher : teachers){
            if (existTeacher.getTeacherId().equalsIgnoreCase(id)){
                existTeacher.setFirstName(teacher.getFirstName());
                existTeacher.setLastName(teacher.getLastName());
                existTeacher.setEmail(teacher.getEmail());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Teacher teacher : teachers){
            if (teacher.getTeacherId().equalsIgnoreCase(id)){
                teachers.remove(teacher);
                break;
            }
        }
    }

    @Override
    public Optional<List<Teacher>> addBulk(List<Teacher> teacherList) throws Exception {
        for (Teacher teacher : teachers){
            teacher.setTeacherId(randomStringGenerate.random());
        }
        teachers.addAll(teacherList);
        return Optional.of(teacherList);
    }

    @Override
    public Optional<List<Teacher>> findBy(Key by, String value) throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        for (Teacher teacher : teachers){
            switch (by){
                case firstName -> {
                    if (teacher.getFirstName().toLowerCase().contains(value)){
                        teacherList.add(teacher);
                    }
                }
                case lastName -> {
                    if (teacher.getLastName().toLowerCase().contains(value)){
                        teacherList.add(teacher);
                    }
                }

                case email -> {
                    if (teacher.getEmail().toLowerCase().contains(value)){
                        teacherList.add(teacher);
                    }
                }

            }
        }
        return teacherList.isEmpty() ? Optional.empty() : Optional.of(teacherList);
    }


}
