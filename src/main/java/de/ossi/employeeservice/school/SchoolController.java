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

    private final SchoolRepository schoolRepository;

    @PostMapping("/schools")
    @ResponseStatus(HttpStatus.CREATED)
    //Response Inhalt gleich Request, deshalb hier auch einfach Return DTO
    public SchoolDto create(@Valid @RequestBody SchoolDto schoolDto) {
        var school = toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    private School toSchool(SchoolDto schoolDto) {
        return new School(schoolDto.name());
    }

    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }

    @GetMapping("/schools")
    public List<SchoolDto> findAll() {
        return schoolRepository.findAll().stream()
                               .map(this::toSchoolDto)
                               .toList();
    }
}
