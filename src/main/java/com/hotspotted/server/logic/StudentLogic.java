package com.hotspotted.server.logic;

import com.hotspotted.server.dto.StudentSearch;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.exception.NotAllowedException;

import java.util.List;
import java.util.Optional;

public interface StudentLogic {
    Student createOrUpdate(Student student);
    Optional<Student> findBySub(String sub);
    List<Student> findBySearchParams(StudentSearch search);
    void removeStudent(Student student, Student remover) throws NotAllowedException;
}
