package com.example.studyhub.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class QuizSessionDTORequest {

    private Long studentId;
    private Long subjectId;
    private Long topicId;
    private String difficulty;
    private Integer score;
    private Integer numQuestions;
    private LocalDateTime startTime;
}