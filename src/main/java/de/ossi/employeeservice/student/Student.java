package de.ossi.employeeservice.student;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STUDENTS")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private int age;
}
