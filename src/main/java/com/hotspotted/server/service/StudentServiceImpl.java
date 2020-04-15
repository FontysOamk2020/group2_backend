package com.hotspotted.server.service;

import com.hotspotted.server.entity.Student;
import com.hotspotted.server.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findBySub(String sub) {
        return studentRepository.findBySub(sub);
    }

    @Override
    public Student createOrUpdate(Student student) {
        if (student.getId() != null) {
            Optional<Student> foundStudent = studentRepository.findById(student.getId());

            if (foundStudent.isPresent()) {
                modelMapper.map(student, foundStudent.get());

                return studentRepository.save(foundStudent.get());
            } else {
                return studentRepository.save(student);
            }
        }
        return studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
