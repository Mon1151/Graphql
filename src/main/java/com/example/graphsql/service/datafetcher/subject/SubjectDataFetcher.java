package com.example.graphsql.service.datafetcher.subject;

import com.example.graphsql.entity.Subject;
import com.example.graphsql.repository.StudentRepository;
import com.example.graphsql.repository.SubjectRepository;
import com.example.graphsql.service.datafetcher.BaseFetchData;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectDataFetcher implements BaseFetchData<Subject> {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Page<Subject> get(DataFetchingEnvironment environment) {
        int first = environment.getArgument("pageIndex");
        int end = environment.getArgument("pageSize");
        Pageable pageable = PageRequest.of(first, end, Sort.by(Sort.Direction.ASC, "id"));
        Page<Subject> subjectPage = subjectRepository.findAll(pageable);
        return subjectPage;
    }

    @Override
    public Subject findById(DataFetchingEnvironment environment) {
        int id = environment.getArgument("id");
        return subjectRepository.getById(id);
    }

    @Override
    public Subject create(DataFetchingEnvironment environment) {
        Subject subject = Subject.builder()
                .name(environment.getArgument("name"))
                .student(studentRepository.getById(environment.getArgument("student")))
                .build();
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(DataFetchingEnvironment environment) {
        Subject subject = Subject.builder()
                .id(environment.getArgument("id"))
                .name(environment.getArgument("name"))
                .student(studentRepository.getById(environment.getArgument("student")))
                .build();
        return subjectRepository.save(subject);
    }

    @Override
    public Boolean delete(DataFetchingEnvironment environment) {
        try {
            subjectRepository.deleteById(environment.getArgument("id"));
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
