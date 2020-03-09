package com.hotspotted.server.logic;

import com.hotspotted.server.entity.Student;

import java.util.Optional;

public interface StudentLogic {
    Student createOrUpdate(Student student);
    Optional<Student> findBySub(String sub);
}
