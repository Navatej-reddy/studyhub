package com.example.studyhub.repository;

import com.example.studyhub.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Optional<Leaderboard> findByStudent_StudentIdAndClassGradeAndSchool_SchoolId(Long studentId, Integer classGrade, Long schoolId);

    // List<Leaderboard> findByClassGradeAndSchool_SchoolIdOrderByTotalScoreDesc(Integer classGrade, Long schoolId, org.springframework.data.domain.Pageable pageable);

    @Query(
            value = "SELECT s.name AS studentName, l.total_score AS totalScore " +
                    "FROM leaderboard l " +
                    "LEFT JOIN student s ON l.student_id = s.student_id " +
                    "WHERE l.school_id = :schoolId AND l.class_grade = :classGrade " +
                    "ORDER BY l.total_score DESC " +
                    "LIMIT 10",
            nativeQuery = true)
    List<Object[]> findTop10ByClassGradeAndSchoolId(@Param("classGrade") Integer classGrade, @Param("schoolId") Long schoolId);
}
