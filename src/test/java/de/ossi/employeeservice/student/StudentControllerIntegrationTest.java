package de.ossi.employeeservice.student;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        assertThat(controller.getStudentById(1)).isNotNull();
    }

    @Test
    void deleteShouldRemoveStudent() {
        assertThat(controller.getStudentById(4)).isNotNull();
        controller.deleteById(4);
        assertThat(controller.getAllStudents())
                .areNot(new Condition<>(student -> student.getId().equals(4), "ID=4 Should not be found"));
    }
}
