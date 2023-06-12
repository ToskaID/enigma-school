package com.example.EnigmaSchool.controller;


import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.model.response.SuccessResponse;
import com.example.EnigmaSchool.service.TeacherService;
import com.example.EnigmaSchool.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping
    public ResponseEntity create(@RequestBody Teacher teacher){
        Teacher newTeacher = teacherService.create(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Teacher>("Success create",newTeacher));
    }

    @GetMapping
    public  ResponseEntity getAll(){
        List<Teacher> teachers = teacherService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Teacher>>("Success",teachers));
    }
    @GetMapping("/{id}")
    public  ResponseEntity getById (@PathVariable String id){
        Optional<Teacher> teacher = teacherService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Teacher>>("Success get " + id ,teacher));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        teacherService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("success delete student with id " + id,null));
    }
    @PutMapping("/{id}")
    public  ResponseEntity update(@PathVariable String id,@RequestBody Teacher teacher){
        teacherService.update(teacher,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Teacher>("success update student",teacher));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam Key key, @RequestParam String value) {
        Optional<List<Teacher>> teachers = teacherService.findBy(key, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Teacher>>>("Success", teachers));
    }

    @PostMapping(path = "/bulk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBulk(@RequestBody List<Teacher> teacherList){
        Optional<List<Teacher>> teachers = teacherService.addBulk(teacherList);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Optional<List<Teacher>>>("Success",teachers));
    }
}
