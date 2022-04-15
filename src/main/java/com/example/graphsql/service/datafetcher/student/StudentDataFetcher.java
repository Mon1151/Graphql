package com.example.graphsql.service.datafetcher.student;

import com.example.graphsql.entity.Student;
import com.example.graphsql.repository.StudentRepository;
import com.example.graphsql.service.datafetcher.BaseFetchData;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDataFetcher implements BaseFetchData<Student> {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(DataFetchingEnvironment environment) {
        int id = environment.getArgument("id");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student create(DataFetchingEnvironment environment) {
        Student student = Student.builder()
                .name(environment.getArgument("name"))
                .address(environment.getArgument("address"))
                .build();
        return studentRepository.save(student);
    }

    @Override
    public Boolean delete(DataFetchingEnvironment environment) {
        int id = environment.getArgument("id");
        try {
            studentRepository.deleteById(id);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Student update(DataFetchingEnvironment environment) {
        Student student = Student.builder()
                .id(environment.getArgument("id"))
                .name(environment.getArgument("name"))
                .address((environment.getArgument("address")))
                .build();
        return studentRepository.save(student);
    }
}
