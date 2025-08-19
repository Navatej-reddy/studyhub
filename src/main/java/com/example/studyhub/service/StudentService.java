package com.example.studyhub.service;

import com.example.studyhub.entity.Student;

public interface StudentService {
    Student createStudent(Student student);
    Student getStudentById(Long id);
}
