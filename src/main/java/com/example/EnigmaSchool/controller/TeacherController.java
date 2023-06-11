package com.example.EnigmaSchool.controller;


import com.example.EnigmaSchool.model.Teacher;
import com.example.EnigmaSchool.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.create(teacher));
    }

    @GetMapping
    public  ResponseEntity getAll(){
        List<Teacher> teachers = teacherService.getAll();
        if (teachers.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Masih Kosong ess");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    @GetMapping("/{id}")
    public  ResponseEntity getById (@PathVariable String id){
        Optional<Teacher> teacher = teacherService.getById(id);
        if (teacher.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Tidak Ditemukan Ess");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(teacher);
    }
}
