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
        assertThat(controller.getAllStudents()).isNotEmpty();
    }

    @Test
    void findByIdShouldReturnResult() {
        assertThat(controller.getStudentById(10001)).isNotNull();
    }

    @DirtiesContext
    @Test
    void deleteShouldRemoveStudent() {
        //TODO Assert ResponseStatus
        Integer studentId = 10004;
        assertThat(controller.getStudentById(studentId)).isNotNull();
        controller.deleteById(studentId);
        assertThatNoStudentWithIdFound(studentId);
    }

    @DirtiesContext
    @Test
    void createShouldAddNewStudent() {
        //TODO Assert ResponseStatus
        String studentName = "NewStudent";
        Condition<Student> shouldHaveLastName = new Condition<>(s -> "NewStudent".equals(s.getLastname()), "No Student should have Lastname=" + studentName);
        assertThat(controller.getAllStudents()).areNot(shouldHaveLastName);
        Student student = createStudent(studentName);
        controller.createStudent(student);
        assertThat(controller.getAllStudents()).areExactly(1, shouldHaveLastName);
    }

    private void assertThatNoStudentWithIdFound(Integer studentId) {
        //TODO repo.existsById verwenden?
        Assertions.assertThatThrownBy(() -> controller.getStudentById(studentId))
                  .isInstanceOf(EntityNotFoundException.class)
                  .hasMessageContaining("" + studentId);
    }

    private Student createStudent(String studentName) {
        Student student = new Student();
        student.setEmail("email" + studentName);
        student.setAge(18);
        student.setLastname(studentName);
        student.setFirstname(studentName);
        return student;
    }
}
