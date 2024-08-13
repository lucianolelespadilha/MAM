package com.llp.amm.repository;

import com.llp.amm.entity.Department;
import com.llp.amm.entity.Equipment;
import com.llp.amm.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    boolean existsByNameAndDepartmentAndSector(String name, Department department, Sector sector);
}
