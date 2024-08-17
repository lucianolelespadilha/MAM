package com.llp.mam.dtos;

import com.llp.mam.entity.Department;
import com.llp.mam.entity.Sector;
import jakarta.validation.constraints.NotBlank;

public record SectorDto(

        Long id,
        @NotBlank
        String name,
        @NotBlank
        Long departmentId
) {

    public static SectorDto fromEntity(Sector sector) {

        return new SectorDto(
                sector.getId(),
                sector.getName(),
                sector.getDepartment().getId()
        );
    }

    public Sector toEntity(Department department) {
        Sector sector = new Sector();
        sector.setId(this.id);
        sector.setName(this.name);
        sector.setDepartment(department);
        return sector;

    }


}