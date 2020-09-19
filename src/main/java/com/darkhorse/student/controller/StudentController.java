package com.darkhorse.student.controller;

import com.darkhorse.student.exception.RecordNotFoundException;
import com.darkhorse.student.model.StudentEntity;
import com.darkhorse.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents(){
        List<StudentEntity> list = studentService.getAllStudents();
        return new ResponseEntity<List<StudentEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        StudentEntity entity = studentService.getStudentById(id);
        return new ResponseEntity<StudentEntity>(
                entity,
                new HttpHeaders(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<StudentEntity> createOrUpdateStudent(@RequestBody StudentEntity student)
            throws RecordNotFoundException
    {
        StudentEntity entity = studentService.createOrUpdateStudent(student);
        return new ResponseEntity<StudentEntity>(
                    entity,
                    new HttpHeaders(),
                    HttpStatus.OK
            );
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteStudentById(@PathVariable("id") Long id)
            throws RecordNotFoundException
    {
        studentService.deleteStudentById(id);
        return HttpStatus.FORBIDDEN;
    }
}