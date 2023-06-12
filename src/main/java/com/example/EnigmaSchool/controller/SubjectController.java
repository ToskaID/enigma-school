package com.example.EnigmaSchool.controller;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.model.Subject;
import com.example.EnigmaSchool.model.response.SuccessResponse;
import com.example.EnigmaSchool.service.SubjectService;
import com.example.EnigmaSchool.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        Subject newSubject = subjectService.create(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Subject>("Success create",newSubject));
    }

    @GetMapping
    public  ResponseEntity getAll(){
        List<Subject> subjects = subjectService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Subject>>("Success",subjects));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Optional<Subject> subject = subjectService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Subject>>("Success get " + id ,subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        subjectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("success delete student with id " + id,null));
    }
    @PutMapping("/{id}")
    public  ResponseEntity update(@PathVariable String id,@RequestBody Subject subject){
        subjectService.update(subject,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Subject>("success update student",subject));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam Key key, @RequestParam String value) {
        Optional<List<Subject>> subjects = subjectService.findBy(key, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Subject>>>("Success", subjects));
    }

    @PostMapping(path = "/bulk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBulk(@RequestBody List<Subject> subjectList){
        Optional<List<Subject>> subjects = subjectService.addBulk(subjectList);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Optional<List<Subject>>>("Success",subjects));
    }
}
