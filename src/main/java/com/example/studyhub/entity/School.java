package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "school")
@Data
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String city;
    private String state;
    private String country;

    private LocalDateTime createdAt = LocalDateTime.now();
}
