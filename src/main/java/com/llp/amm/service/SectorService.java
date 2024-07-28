package com.llp.amm.service;

import com.llp.amm.dto.SectorDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.exception.DepartmentAlreadyExistsException;
import com.llp.amm.exception.DepartmentNotRegistered;
import com.llp.amm.exception.SectorAlreadyExistsException;
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
                .orElseThrow(()-> new DepartmentNotRegistered());

        if(sectorRepository.existsByName(sectorDto.name())){
            throw new SectorAlreadyExistsException(sectorDto.name());
        }

        Sector sector = sectorDto.toEntity(department);
        return sectorRepository.save(sector);

    }
}
