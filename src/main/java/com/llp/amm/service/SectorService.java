package com.llp.amm.service;

import com.llp.amm.dto.SectorDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.repository.DepartmentRepository;
import com.llp.amm.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public Sector addSector(SectorDto sectorDto) {
        Department department = departmentRepository.findById(sectorDto.departmentId())
                .orElseThrow(()-> new IllegalArgumentException("Department not found"));

        Sector sector = sectorDto.toEntity(department);
        return sectorRepository.save(sector);

    }
}
