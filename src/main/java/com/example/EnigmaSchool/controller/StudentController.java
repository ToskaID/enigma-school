package com.example.EnigmaSchool.controller;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.service.IService;
import com.example.EnigmaSchool.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(student));
    }

    @GetMapping
    public ResponseEntity getAll(){

        List<Student> students = studentService.getAll();
        if (students.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Masih Kosong ess");
        }
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Optional<Student> student = studentService.getById(id);
        if (student.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Tidak Ditemukan ess");
        }
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
