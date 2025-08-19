package com.example.studyhub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "topic")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String name;
}
