package com.llp.amm.dto;

import com.llp.amm.entity.Department;
import jakarta.validation.constraints.NotBlank;

public record DepartmentDto(@NotBlank String name) {



    public Department toEntity() {
        Department department = new Department();
        department.setName(this.name);
        return department;

    }
}
