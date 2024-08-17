package com.llp.mam.repository;

import com.llp.mam.entity.Department;
import com.llp.mam.entity.Equipment;
import com.llp.mam.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    boolean existsByNameAndDepartmentAndSector(String name, Department department, Sector sector);
}
