package de.ossi.employeeservice.student;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private StudentService studentService;

    private final Student emtpyStudent = new Student();

    @Test
    void repoReturns1ResultFindAllShouldReturn1Result() {
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(emtpyStudent));
        assertThat(studentService.findAll()).hasSize(1);
    }

    @Test
    void noResultIsFoundByIdShouldThrowException() {
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> studentService.findStudentById(999))
                  .isInstanceOf(EntityNotFoundException.class)
                  .hasMessageContaining("999");
    }

    @Test
    void resultIsFoundByIdShouldReturnResult() {
        Student student = new Student();
        student.setId(111);
        student.setLastname("lastname");
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any())).thenReturn(new StudentResponseDto("firstname", "lastname", "email"));
        StudentResponseDto byId = studentService.findStudentById(111);
        assertThat(byId)
                .extracting(StudentResponseDto::lastname)
                .isEqualTo("lastname");
    }
}
