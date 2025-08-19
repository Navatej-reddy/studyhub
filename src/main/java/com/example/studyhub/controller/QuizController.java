package com.example.studyhub.controller;

import com.example.studyhub.dto.QuizResultDTO;
import com.example.studyhub.dto.QuizSessionDTORequest;
import com.example.studyhub.dto.QuizSessionDTOResponse;
import com.example.studyhub.entity.QuizSession;
import com.example.studyhub.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/start")
    public ResponseEntity<QuizSessionDTOResponse> startQuiz(@RequestBody QuizSessionDTORequest quizSessionDTORequest) {
        QuizSessionDTOResponse session = quizService.startQuiz(quizSessionDTORequest);
        return ResponseEntity.ok(session);
    }

    @GetMapping("/submit")
    public ResponseEntity<Object> submit(@RequestParam Long attemptId, @RequestParam String answer) {
        quizService.submitAnswer(attemptId,answer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/end/{quizId}")
    public ResponseEntity<QuizResultDTO> endQuiz(@PathVariable Long quizId) {
        QuizResultDTO session = quizService.endQuiz(quizId);
        return ResponseEntity.ok(session);
    }
}
