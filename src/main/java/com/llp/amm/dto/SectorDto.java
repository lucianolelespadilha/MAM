package com.llp.amm.dto;

import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import jakarta.validation.constraints.NotBlank;

public record SectorDto(
        @NotBlank
        String name,
        @NotBlank
        Long  departmentId
) {
    public Sector toEntity(Department department) {
        Sector sector = new Sector();
        sector.setName(this.name);
        sector.setDepartment(department);
        return sector;

    }

}
