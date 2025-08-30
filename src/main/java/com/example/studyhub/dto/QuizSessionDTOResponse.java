package com.example.studyhub.dto;

import com.example.studyhub.entity.QuizSession;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizSessionDTOResponse {
    private Long studentId;
    private Long subjectId;
    private Long topicId;
    private Long subtopicId;
    private String difficulty;
    private Integer score;
    private Integer numQuestions;
    private LocalDateTime startTime;
    private List<QuizQuestionAttemptDTO> questions;
    private long quizId;

    public static QuizSessionDTOResponse from(QuizSession session, List<QuizQuestionAttemptDTO> questions) {
        QuizSessionDTOResponse dto = new QuizSessionDTOResponse();
        dto.quizId = session.getQuizId();
        dto.studentId = session.getStudent().getStudentId();
        dto.subjectId = session.getSubject().getSubjectId();
        dto.topicId = session.getTopic().getTopicId();
        dto.difficulty = session.getDifficulty().name();
        dto.score = session.getScore();
        dto.startTime = session.getStartTime();
        dto.questions = questions;
        return dto;
    }
}
