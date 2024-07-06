package de.ossi.employeeservice.student;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    //TODO Post with create Student
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @GetMapping("/students/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        studentRepository.deleteById(id);
    }
}
