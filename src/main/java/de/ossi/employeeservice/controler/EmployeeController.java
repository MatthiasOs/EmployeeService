package de.ossi.employeeservice.controler;

import de.ossi.employeeservice.model.Employee;
import de.ossi.employeeservice.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

//    @Autowired
    public EmployeeController(EmployeeRepository repository) {this.repository = repository;}

    @GetMapping
    public List<Employee> findAll(){
        return repository.findAll();
    }
}
