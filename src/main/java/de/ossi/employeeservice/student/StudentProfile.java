package de.ossi.employeeservice.student;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue
    private Integer id;
    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id") //StudentProfile ist die sekundäre Tabelle, also ist hier die joined colum
    private Student student;

    public StudentProfile(String bio) {
        this.bio = bio;
    }
}
