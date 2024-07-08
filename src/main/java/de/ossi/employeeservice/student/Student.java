package de.ossi.employeeservice.student;

import de.ossi.employeeservice.school.School;
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

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToOne(mappedBy = "student", //Wie das Feld in StudentProfile vom Student heißt!
            cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
}
