package com.example.graphsql.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
