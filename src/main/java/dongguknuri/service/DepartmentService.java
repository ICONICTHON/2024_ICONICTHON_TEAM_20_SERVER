package dongguknuri.service;

import dongguknuri.domain.Department;
import dongguknuri.dto.request.CreateDepartmentDto;
import dongguknuri.dto.response.DepartmentResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.DepartmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(department -> DepartmentResponseDto.of(
                        department.getDepartmentId(),
                        department.getName()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartmentResponseDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));

        return DepartmentResponseDto.builder()
                .departmentId(department.getDepartmentId())
                .name(department.getName())
                .build();
    }

    @Transactional
    public boolean createDepartment(CreateDepartmentDto createDepartmentDto) {
        departmentRepository.save(Department.builder()
                .name(createDepartmentDto.name())
                .build());

        return Boolean.TRUE;
    }


    @Transactional
    public boolean deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT));

        departmentRepository.delete(department);
        return Boolean.TRUE;

    }
}
