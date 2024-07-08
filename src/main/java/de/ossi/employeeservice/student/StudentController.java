package de.ossi.employeeservice.student;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {this.studentRepository = studentRepository;}

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable("id") Integer id) {
        return studentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("No Student found for ID: " + id));
    }

    @GetMapping("/students/search/{lastname}")
    public List<Student> findStudentsByLastName(@PathVariable("lastname") String lastname) {
        return studentRepository.findAllByLastnameContaining(lastname);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@Valid @RequestBody Student student) {
        studentRepository.save(student);
    }

    @DeleteMapping("/students/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        studentRepository.deleteById(id);
    }
}
