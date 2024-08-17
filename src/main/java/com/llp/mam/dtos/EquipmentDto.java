package com.llp.mam.dtos;

import com.llp.mam.entity.Department;
import com.llp.mam.entity.Equipment;
import com.llp.mam.entity.Sector;


public record EquipmentDto(
        Long tag,
        String name,
        Long  departmentId,
        Long sectorId

) {


    public static EquipmentDto fromEntity(Equipment equipment) {
        return new EquipmentDto(
                equipment.getTag(),
                equipment.getName(),
                equipment.getDepartment().getId(),
                equipment.getSector().getId()
        );

    }

    public Equipment toEntity(Department department, Sector sector) {
        Equipment equipment = new Equipment();
        equipment.setTag(this.tag);
        equipment.setName(this.name);
        equipment.setDepartment(department);
        equipment.setSector(sector);
        return equipment;

    }

    public static EquipmentDto toResponse(Equipment equipment){
        DepartmentDto departmentDto = DepartmentDto.fromEntity(equipment.getDepartment(), equipment.getSector());
        return new EquipmentDto(
                equipment.getTag(),
                equipment.getName(),
                departmentDto.id(),
                departmentDto.sector().id()

        );

    }


}
