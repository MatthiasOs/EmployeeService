package de.ossi.employeeservice.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public List<StudentResponseDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public StudentResponseDto findStudentById(@PathVariable("id") Integer id) {
        return studentService.findStudentById(id);
    }

    @GetMapping("/students/search/{lastname}")
    public List<StudentResponseDto> findStudentsByLastName(@PathVariable("lastname") String lastname) {
        return studentService.findStudentsByLastName(lastname);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.create(studentDto);
    }


    @DeleteMapping("/students/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
    }
}
