package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subtopic")
@Data
public class Subtopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subtopicId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String name;
}
