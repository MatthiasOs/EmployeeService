package de.ossi.employeeservice.student;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Integer> {
    List<Student> findAllByLastnameContaining(String lastnameParam);
}
