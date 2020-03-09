package com.hotspotted.server.logic;

import com.hotspotted.server.entity.Student;
import com.hotspotted.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentLogicImpl implements StudentLogic {
    private final StudentService studentService;

    @Autowired
    public StudentLogicImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Student createOrUpdate(Student student) {
        return studentService.createOrUpdate(student);
    }

    @Override
    public Optional<Student> findBySub(String sub) {
        return studentService.findBySub(sub);
    }
}
