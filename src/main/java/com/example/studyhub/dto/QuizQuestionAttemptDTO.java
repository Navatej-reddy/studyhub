package com.example.studyhub.dto;

import lombok.Data;

@Data
public class QuizQuestionAttemptDTO {
    private Long attemptId;
    private Long questionId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}