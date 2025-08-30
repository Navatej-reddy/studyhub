package com.example.studyhub.repository;

import com.example.studyhub.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q.question_id FROM question q WHERE q.topic_id = :topicId AND q.difficulty = :difficulty AND q.class_grade = :classGrade", nativeQuery = true)
    List<Long> findIdsByFiltersNative(@Param("topicId") Long topicId,
                                      @Param("difficulty") String difficulty,
                                      @Param("classGrade") Integer classGrade);

    List<Question> findByQuestionIdIn(List<Long> questionIds);}
