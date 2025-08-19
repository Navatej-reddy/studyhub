package com.example.studyhub.dto;

public class QuizResultDTO {
    public Long quizId;
    public int score;
    public int totalQuestions;

    public static QuizResultDTO from(Long quizId, int score, int totalQuestions) {
        QuizResultDTO r = new QuizResultDTO();
        r.quizId = quizId;
        r.score = score;
        r.totalQuestions = totalQuestions;
        return r;
    }
}