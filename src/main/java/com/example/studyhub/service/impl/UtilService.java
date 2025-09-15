package com.example.studyhub.service.impl;

import com.example.studyhub.dto.TopicRequest;
import com.example.studyhub.entity.Subject;
import com.example.studyhub.entity.Topic;
import com.example.studyhub.repository.SubjectRepository;
import com.example.studyhub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtilService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Long getTopicIdByNameAndSubject(TopicRequest request) {
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        Topic topic = topicRepository.findByNameAndSubject(request.getName(), subject);
               // .orElseThrow(() -> new IllegalArgumentException("Topic not found"));
        return topic.getTopicId();
    }
}
