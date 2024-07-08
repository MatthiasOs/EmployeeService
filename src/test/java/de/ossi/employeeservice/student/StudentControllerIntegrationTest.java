package de.ossi.employeeservice.student;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentControllerIntegrationTest {

    @Autowired
    private StudentController controller;

    @Test
    void findAllShouldHaveResults() {
        assertThat(controller.findAll()).isNotEmpty();
    }

    @Test
    void findByIdShouldReturnResult() {
        assertThat(controller.findStudentById(10001)).isNotNull();
    }

    @DirtiesContext
    @Test
    void deleteShouldRemoveStudent() {
        //TODO Assert ResponseStatus
        Integer studentId = 10004;
        assertThat(controller.findStudentById(studentId)).isNotNull();
        controller.deleteById(studentId);
        assertThatNoStudentWithIdFound(studentId);
    }

    @DirtiesContext
    @Test
    void createShouldAddNewStudent() {
        //TODO Assert ResponseStatus
        String studentName = "NewStudent";
        Condition<StudentResponseDto> shouldHaveLastName = new Condition<>(s -> "NewStudent".equals(s.lastname()), "No Student should have Lastname=" + studentName);
        assertThat(controller.findAll()).areNot(shouldHaveLastName);
        StudentDto studentDto = createDto(studentName);
        StudentResponseDto savedStudent = controller.createStudent(studentDto);
        assertThat(savedStudent).extracting(StudentResponseDto::lastname).isEqualTo("NewStudent");
        assertThat(controller.findAll()).areExactly(1, shouldHaveLastName);
    }

    private void assertThatNoStudentWithIdFound(Integer studentId) {
        //TODO repo.existsById verwenden?
        Assertions.assertThatThrownBy(() -> controller.findStudentById(studentId))
                  .isInstanceOf(EntityNotFoundException.class)
                  .hasMessageContaining("" + studentId);
    }

    private StudentDto createDto(String studentName) {
        return new StudentDto(studentName, studentName, "email" + studentName, null);
    }
}
