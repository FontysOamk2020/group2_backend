package com.hotspotted.server.controller;

import com.hotspotted.server.entity.Student;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Tag(name = "student", description = "All endpoints regarding student")
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/me")
    public Student getStudent(@Parameter(hidden = true) @RequestAttribute("student") Student student) {
       return student;
    }
}
