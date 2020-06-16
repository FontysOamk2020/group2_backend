package com.hotspotted.server.controller;

import com.hotspotted.server.controller.enums.Response;
import com.hotspotted.server.dto.StudentSearch;
import com.hotspotted.server.entity.Student;
import com.hotspotted.server.exception.NotAllowedException;
import com.hotspotted.server.logic.StudentLogic;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController()
@Tag(name = "Student", description = "All endpoints regarding student")
@RequestMapping("/student")
public class StudentController {
    private final StudentLogic studentLogic;
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentLogic studentLogic) {
        this.studentLogic = studentLogic;
    }

    @GetMapping("/me")
    public Student getStudent(@Parameter(hidden = true) @RequestAttribute("student") Student student) {
       return studentLogic.findBySub(student.getSub())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));
    }

    @PreAuthorize("hasAuthority('read:student')")
    @GetMapping("/search")
    public List<Student> getAll(@Valid StudentSearch studentSearch) {
        return studentLogic.findBySearchParams(studentSearch);
    }

    @PreAuthorize("hasAuthority('write:student')")
    @DeleteMapping("{sub}")
    public ResponseEntity deleteStudent(@PathVariable("sub") String sub,  @Parameter(hidden = true) @RequestAttribute("student") Student student) {
        Student foundStudent = studentLogic.findBySub(sub)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Response.NOT_FOUND.toString()));

        try {
            this.studentLogic.removeStudent(foundStudent, student);
            return ResponseEntity.ok().build();
        } catch (NotAllowedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            logger.error("Something went wrong", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Response.UNEXPECTED_ERROR.toString());
        }
    }
}
