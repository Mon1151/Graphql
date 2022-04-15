package com.example.graphsql.service;

import com.example.graphsql.repository.StudentRepository;
import com.example.graphsql.service.datafetcher.student.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Value("classpath:common.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private StudentDataFetcher studentDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allStudents", studentDataFetcher::get)
                        .dataFetcher("student", studentDataFetcher::findById)
                        .dataFetcher("addStudent", studentDataFetcher::create)
                        .dataFetcher("updateStudent", studentDataFetcher::update)
                        .dataFetcher("deleteStudent", studentDataFetcher::delete))
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
