package com.example.studyhub.service;

import com.example.studyhub.entity.School;

public interface SchoolService {
    School createSchool(School school);
    School getSchoolById(Long id);
}