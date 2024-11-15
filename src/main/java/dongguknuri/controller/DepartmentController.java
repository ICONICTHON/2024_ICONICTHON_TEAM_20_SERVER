package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateDepartmentDto;
import dongguknuri.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseDto<?> getAllDepartments() {
        return ResponseDto.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentId}")
    public ResponseDto<?> getDepartmentById(@PathVariable Long departmentId) {
        return ResponseDto.ok(departmentService.getDepartmentById(departmentId));
    }

    @PostMapping
    public ResponseDto<?> createDepartment(@RequestBody CreateDepartmentDto createDepartmentDto) {
        return ResponseDto.created(departmentService.createDepartment(createDepartmentDto));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseDto<?> deleteDepartment(@PathVariable Long departmentId) {
        return ResponseDto.ok(departmentService.deleteDepartment(departmentId));
    }
}
