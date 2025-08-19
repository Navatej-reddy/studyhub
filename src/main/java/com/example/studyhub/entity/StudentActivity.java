package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "studentactivity")
@Data
public class StudentActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Integer logins = 0;
    private Integer doubtsAsked = 0;
    private Integer highScore = 0;

}
