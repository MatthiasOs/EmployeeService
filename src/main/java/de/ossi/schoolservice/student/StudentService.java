package de.ossi.schoolservice.student;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponseDto create(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAll() {
        return studentRepository.findAll().stream()
                                .map(studentMapper::toStudentResponseDto).toList();
    }

    public StudentResponseDto findStudentById(Integer id) {
        return studentRepository.findById(id)
                                .map(studentMapper::toStudentResponseDto)
                                .orElseThrow(() -> new EntityNotFoundException("No Student found for ID: " + id));
    }

    public List<StudentResponseDto> findStudentsByLastName(String lastname) {
        return studentRepository.findAllByLastnameContaining(lastname).stream()
                                .map(studentMapper::toStudentResponseDto).toList();
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
