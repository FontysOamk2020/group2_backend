package com.hotspotted.server.logic;

import com.hotspotted.server.entity.Student;
import com.hotspotted.server.exception.NotAllowedException;

import java.util.Optional;

public interface StudentLogic {
    Student createOrUpdate(Student student);
    Optional<Student> findBySub(String sub);
    void removeStudent(Student student, Student remover) throws NotAllowedException;
}
