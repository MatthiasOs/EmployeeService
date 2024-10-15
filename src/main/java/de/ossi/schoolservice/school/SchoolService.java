package de.ossi.schoolservice.school;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolDto create(SchoolDto schoolDto) {
        var school = schoolMapper.toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    public List<SchoolDto> findAll() {
        return schoolRepository.findAll().stream()
                               .map(schoolMapper::toSchoolDto)
                               .toList();
    }
}
