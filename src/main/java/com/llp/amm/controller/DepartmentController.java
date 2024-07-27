package com.llp.amm.controller;


import com.llp.amm.dto.DepartmentDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
