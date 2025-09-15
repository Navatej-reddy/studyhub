package com.example.studyhub.controller;

import com.example.studyhub.dto.TopicRequest;
import com.example.studyhub.service.impl.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//This controller acts as a util controller for other controller
@RestController
@RequestMapping("/api/util/")
public class UtilController {

    @Autowired
    private UtilService utilService;

    // user sends the topicname and subject id , based on that we need to return the topicID
    @PostMapping("/getTopicID")
    public Long getTopic(@RequestBody TopicRequest request) {
        return utilService.getTopicIdByNameAndSubject(request);
    }

}
