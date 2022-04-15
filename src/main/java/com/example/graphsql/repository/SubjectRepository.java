package com.example.graphsql.repository;

import com.example.graphsql.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Page<Subject> findAll(Pageable pageable);
}
