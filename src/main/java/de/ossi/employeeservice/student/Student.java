package de.ossi.employeeservice.student;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENTS")
public record Student(@Id @GeneratedValue Integer id,
                      @Column(name = "first_name", length = 20) String firstname,
                      String lastname,
                      @Column(unique = true) String email,
                      int age) {
}
