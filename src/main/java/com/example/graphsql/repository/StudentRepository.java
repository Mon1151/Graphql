package com.example.graphsql.repository;

import com.example.graphsql.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAll(Pageable pageable);
}
