package de.ossi.schoolservice.school;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.ossi.schoolservice.student.Student;
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
    @JsonManagedReference //Damit keine Schleife beim parsen des JSON entsteht
    private List<Student> students = new ArrayList<>();

    public School(String name) {
        this.name = name;
    }
}
