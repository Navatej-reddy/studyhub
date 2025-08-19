package com.example.studyhub.repository;

import com.example.studyhub.entity.QuizAttemptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptDetailRepository extends JpaRepository<QuizAttemptDetail, Long> {
    List<QuizAttemptDetail> findByQuizSession_QuizId(Long quizId);}
