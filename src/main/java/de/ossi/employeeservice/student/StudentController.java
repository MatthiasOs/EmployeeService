package de.ossi.employeeservice.student;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {this.studentRepository = studentRepository;}

    @GetMapping("/students")
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable("id") Integer id) {
        return studentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("No Student found for ID: " + id));
    }
}
