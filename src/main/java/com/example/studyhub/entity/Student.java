package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private String name;
    private Integer classGrade;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt = LocalDateTime.now();

}
