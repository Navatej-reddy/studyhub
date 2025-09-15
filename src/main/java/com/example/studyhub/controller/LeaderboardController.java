// src/main/java/com/example/studyhub/controller/LeaderboardController.java
package com.example.studyhub.controller;

import com.example.studyhub.dto.LeaderboardDTO;
import com.example.studyhub.entity.Leaderboard;
import com.example.studyhub.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping
    public List<LeaderboardDTO> getLeaderboard(
            @RequestParam Integer classGrade,
            @RequestParam Long schoolId) {
        return leaderboardService.getLeaderboardByClassAndSchool(classGrade, schoolId);
    }
}