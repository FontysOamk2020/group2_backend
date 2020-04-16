package com.hotspotted.server.service;

import com.hotspotted.server.dto.StudentSearch;
import com.hotspotted.server.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student createOrUpdate(Student student);
    Optional<Student> findBySub(String sub);
    List<Student> findBySearchParams(StudentSearch search);
    void delete(Student student);
}