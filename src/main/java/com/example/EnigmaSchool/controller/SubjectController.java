package com.example.EnigmaSchool.controller;

import com.example.EnigmaSchool.model.Subject;
import com.example.EnigmaSchool.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping
    public ResponseEntity create(@RequestBody Subject subject){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.create(subject));
    }

    @GetMapping
    public  ResponseEntity getAll(){
        List<Subject> subjects = subjectService.getAll();
        if (subjects.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Masih Koson Ess");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Optional<Subject> subject = subjectService.getById(id);
        if (subject.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Data Tidak Ditemukan Ess");
        }
        return ResponseEntity.status(HttpStatus.OK).body(subject);
    }
}
