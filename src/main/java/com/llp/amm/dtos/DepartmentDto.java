package com.llp.amm.dtos;

import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;

import java.util.Set;

public record DepartmentDto(Long id, String name, SectorDto sector) {



    public static DepartmentDto fromEntity(Department department, Sector sector) {
        return new DepartmentDto(department.getId(), department.getName(), SectorDto.fromEntity(sector));
    }

    public static DepartmentDto fromEntity(Department department) {
        return new DepartmentDto(department.getId(), department.getName(), null);
    }

    public Department toEntity() {
        Department department = new Department();
        department.setId(this.id);
        department.setName(this.name);

        return department;

    }


}