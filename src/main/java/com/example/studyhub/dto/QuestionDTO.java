package com.example.studyhub.dto;

import com.example.studyhub.entity.Question;

public class QuestionDTO {
    public Long questionId;
    public String questionText;
    public String optionA, optionB, optionC, optionD;

    public static QuestionDTO fromEntityWithoutAnswer(Question q) {
        QuestionDTO d = new QuestionDTO();
        d.questionId = q.getQuestionId();
        d.questionText = q.getQuestionText();
        d.optionA = q.getOptionA();
        d.optionB = q.getOptionB();
        d.optionC = q.getOptionC();
        d.optionD = q.getOptionD();
        return d;
    }
}