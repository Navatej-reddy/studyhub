package com.example.studyhub.service.impl;

import com.example.studyhub.dto.LeaderboardDTO;
import com.example.studyhub.entity.Leaderboard;
import com.example.studyhub.repository.LeaderboardRepository;
import com.example.studyhub.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    @Autowired
    private LeaderboardRepository leaderboardRepository;


    @Override
    public List<LeaderboardDTO> getLeaderboardByClassAndSchool(Integer classGrade, Long schoolId) {
        return leaderboardRepository.findTop10ByClassGradeAndSchoolId(classGrade, schoolId)
                .stream()
                .map(row -> new LeaderboardDTO((String) row[0], ((Number) row[1]).intValue()))
                .toList();
    }
}