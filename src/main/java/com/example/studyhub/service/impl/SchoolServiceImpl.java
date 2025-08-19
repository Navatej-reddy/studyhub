package com.example.studyhub.service.impl;

import com.example.studyhub.entity.School;
import com.example.studyhub.repository.SchoolRepository;
import com.example.studyhub.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public School getSchoolById(Long id) {
        Optional<School> school = schoolRepository.findById(id);
        return school.orElse(null);
    }
}