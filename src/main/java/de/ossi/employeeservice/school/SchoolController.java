package de.ossi.employeeservice.school;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping("/schools")
    @ResponseStatus(HttpStatus.CREATED)
    //Response Inhalt gleich Request, deshalb hier auch einfach Return DTO
    public SchoolDto create(@Valid @RequestBody SchoolDto schoolDto) {
        return schoolService.create(schoolDto);
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolService.findAll();
    }
}
