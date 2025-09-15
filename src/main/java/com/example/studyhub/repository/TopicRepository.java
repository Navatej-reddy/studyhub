package com.example.studyhub.repository;

import com.example.studyhub.entity.Subject;
import com.example.studyhub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByNameAndSubject(String name, Subject subject);
}
