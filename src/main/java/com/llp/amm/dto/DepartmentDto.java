package com.llp.amm.dto;

import com.llp.amm.entity.Department;
import jakarta.validation.constraints.NotBlank;

public record DepartmentDto(Long id, String name) {


    public Department toEntity() {
        Department department = new Department();
        department.setName(this.name);
        return department;

    }

    public static DepartmentDto fromEntity(Department department) {
        return new DepartmentDto(department.getId(), department.getName());
    }
}
