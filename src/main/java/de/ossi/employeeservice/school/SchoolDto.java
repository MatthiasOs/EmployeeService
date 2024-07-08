package de.ossi.employeeservice.school;

import jakarta.validation.constraints.NotNull;

public record SchoolDto(@NotNull String name) {
}
