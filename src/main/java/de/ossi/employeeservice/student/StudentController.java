package de.ossi.employeeservice.student;

import de.ossi.employeeservice.school.School;
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
    public StudentResponseDto createStudent(@Valid @RequestBody StudentDto studentDto) {
        var student = toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return toStudentResponseDto(savedStudent);
    }

    private Student toStudent(StudentDto studentDto) {
        var student = new Student();
        student.setFirstname(studentDto.firstname());
        student.setLastname(studentDto.lastname());
        student.setEmail(studentDto.email());
        var school = new School();
        school.setId(10001); //TODO weil es die school schon gibt
        student.setSchool(school);
        return student;
    }

    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail());
    }

    @DeleteMapping("/students/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        studentRepository.deleteById(id);
    }
}
