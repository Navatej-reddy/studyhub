package com.example.studyhub.repository;

import com.example.studyhub.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Optional<Leaderboard> findByStudent_StudentIdAndClassGradeAndSchool_SchoolId(Long studentId, Integer classGrade, Long schoolId);}
