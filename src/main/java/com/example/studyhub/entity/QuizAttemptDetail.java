package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "quizattemptdetail")
@Data
public class QuizAttemptDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizSession quizSession;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String studentAnswer;
    
    private Boolean isCorrect;
}
