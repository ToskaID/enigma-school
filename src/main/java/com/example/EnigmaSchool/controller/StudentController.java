package com.example.EnigmaSchool.controller;

import com.example.EnigmaSchool.model.Student;
import com.example.EnigmaSchool.model.response.SuccessResponse;
import com.example.EnigmaSchool.service.IService;
import com.example.EnigmaSchool.service.StudentService;
import com.example.EnigmaSchool.utils.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        Student newStudent = studentService.create(student);
        return ResponseEntity.status(HttpStatus.CREATED).body( new SuccessResponse<Student>("Success Crete",newStudent));
    }

    @GetMapping
    public ResponseEntity getAll(){

        List<Student> studentList = studentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<List<Student>>("Success",studentList));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id){
        Optional<Student> student = studentService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<Student>>("Success get " + id ,student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id){
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("success delete student with id " + id,null));
    }

    @PutMapping("/{id}")
    public  ResponseEntity update(@PathVariable String id,@RequestBody Student student){
        studentService.update(student,id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Student>("success update student",student));
    }

    @GetMapping(params = {"key", "value"})
    public ResponseEntity getBy(@RequestParam Key key, @RequestParam String value) {
        Optional<List<Student>> students = studentService.findBy(key, value);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<Optional<List<Student>>>("Success", students));
    }

    @PostMapping(path = "/bulk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBulk(@RequestBody List<Student> studentList){
        Optional<List<Student>> students = studentService.addBulk(studentList);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<Optional<List<Student>>>("Success",students));
    }


}
