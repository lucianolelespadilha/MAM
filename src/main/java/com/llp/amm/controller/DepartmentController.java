package com.llp.amm.controller;


import com.llp.amm.dto.DepartmentDto;
import com.llp.amm.dto.UserDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.entity.User;
import com.llp.amm.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        Department savedDepartment = departmentService.addDepartment(departmentDto);

        return ResponseEntity.ok(savedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        departmentService.deleteService(id);

        return ResponseEntity.noContent().build();// Retorna HTTP 204 No Content em caso de sucesso
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<Department> departments = departmentService.findAllDepartments();
        List<DepartmentDto> departmentDtos = departments.stream()
                .map(DepartmentDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(departmentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.findDepartmentById(id);
        DepartmentDto departmentDto = DepartmentDto.fromEntity(department);

        return ResponseEntity.ok(departmentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        Department updatedDepartment = departmentService.updateDepartment(id, departmentDto);
        DepartmentDto updatedDepartmentDto = DepartmentDto.fromEntity(updatedDepartment);
        return ResponseEntity.ok(updatedDepartmentDto);

    }

}
