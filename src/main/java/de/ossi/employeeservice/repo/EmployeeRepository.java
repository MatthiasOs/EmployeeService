package de.ossi.employeeservice.repo;

import de.ossi.employeeservice.model.Employee;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> {
}
