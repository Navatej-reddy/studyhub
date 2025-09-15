package com.example.studyhub.dto;

import lombok.Data;

@Data
public class LeaderboardDTO {
    private String studentName;
    private int totalScore;

    public LeaderboardDTO(String studentName, int totalScore) {
        this.studentName = studentName;
        this.totalScore = totalScore;
    }


}