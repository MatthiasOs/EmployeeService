package de.ossi.employeeservice.school;

import de.ossi.employeeservice.student.Student;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @OneToMany(
            mappedBy = "school"
    )
    private List<Student> students = new ArrayList<>();

    public School(String name) {
        this.name = name;
    }
}
