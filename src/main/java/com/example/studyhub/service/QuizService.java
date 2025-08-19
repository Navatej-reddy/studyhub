package com.example.studyhub.service;

import com.example.studyhub.dto.QuizResultDTO;
import com.example.studyhub.dto.QuizSessionDTORequest;
import com.example.studyhub.dto.QuizSessionDTOResponse;
import com.example.studyhub.entity.Question;
import com.example.studyhub.entity.QuizSession;

public interface QuizService {
    QuizSessionDTOResponse startQuiz(QuizSessionDTORequest quizSessionDTORequest);
    void submitAnswer(Long attemptId, String answer);
    QuizResultDTO endQuiz(Long quizId);
}
