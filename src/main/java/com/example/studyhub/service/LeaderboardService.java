package com.example.studyhub.service;

import com.example.studyhub.dto.LeaderboardDTO;
import com.example.studyhub.entity.Leaderboard;
import java.util.List;

public interface LeaderboardService {
    List<LeaderboardDTO> getLeaderboardByClassAndSchool(Integer classGrade, Long schoolId);
}