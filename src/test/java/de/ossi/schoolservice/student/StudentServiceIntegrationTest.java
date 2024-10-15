package de.ossi.schoolservice.student;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    void findAllShouldHaveResults() {
        assertThat(studentService.findAll()).isNotEmpty();
    }

    @Test
    void findByIdShouldReturnResult() {
        assertThat(studentService.findStudentById(10001)).isNotNull();
    }

    @DirtiesContext
    @Test
    void deleteShouldRemoveStudent() {
        //TODO Assert ResponseStatus
        Integer studentId = 10004;
        assertThat(studentService.findStudentById(studentId)).isNotNull();
        studentService.deleteById(studentId);
        assertThatNoStudentWithIdFound(studentId);
    }

    @DirtiesContext
    @Test
    void createShouldAddNewStudent() {
        //TODO Assert ResponseStatus
        String studentName = "NewStudent";
        Condition<StudentResponseDto> shouldHaveLastName = new Condition<>(s -> "NewStudent".equals(s.lastname()), "No Student should have Lastname=" + studentName);
        assertThat(studentService.findAll()).areNot(shouldHaveLastName);
        StudentDto studentDto = createDto(studentName);
        StudentResponseDto savedStudent = studentService.create(studentDto);
        assertThat(savedStudent).extracting(StudentResponseDto::lastname).isEqualTo("NewStudent");
        assertThat(studentService.findAll()).areExactly(1, shouldHaveLastName);
    }

    private void assertThatNoStudentWithIdFound(Integer studentId) {
        //TODO repo.existsById verwenden?
        Assertions.assertThatThrownBy(() -> studentService.findStudentById(studentId))
                  .isInstanceOf(EntityNotFoundException.class)
                  .hasMessageContaining("" + studentId);
    }

    private StudentDto createDto(String studentName) {
        return new StudentDto(studentName, studentName, "email" + studentName, null);
    }
}
