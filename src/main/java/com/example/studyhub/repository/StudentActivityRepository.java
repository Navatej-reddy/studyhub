package com.example.studyhub.repository;

import com.example.studyhub.entity.StudentActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentActivityRepository extends JpaRepository<StudentActivity, Long> {

    Optional<StudentActivity> findByStudent_StudentId(Long studentId);
}
