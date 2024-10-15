package de.ossi.schoolservice.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(@NotEmpty String firstname, @NotEmpty String lastname, @Email String email, Integer schoolId) {
}
