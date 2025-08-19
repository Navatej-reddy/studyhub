package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "leaderboard")
@Data
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaderboardId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private Integer classGrade;
    private Integer totalScore = 0;

}
