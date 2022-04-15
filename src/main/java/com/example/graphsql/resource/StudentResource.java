package com.example.graphsql.resource;

import com.example.graphsql.entity.Student;
import com.example.graphsql.service.StudentService;
import com.example.graphsql.service.SubjectService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class StudentResource {
    @Autowired
    StudentService studentService;
    @Autowired
    SubjectService subjectService;

    @PostMapping("/student")
    public ResponseEntity<Object> getStudent(@RequestBody String query) {
        ExecutionResult execute = studentService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

    @PostMapping("/subject")
    public ResponseEntity<Object> getSubject(@RequestBody String query) {
        ExecutionResult execute = subjectService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
