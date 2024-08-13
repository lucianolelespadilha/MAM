package com.llp.amm.service;

import com.llp.amm.dtos.DepartmentDto;
import com.llp.amm.dtos.EquipmentDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Equipment;
import com.llp.amm.entity.Sector;
import com.llp.amm.exception.DepartmentNotFoundException;
import com.llp.amm.exception.EquipmentAlreadyExistsException;
import com.llp.amm.exception.EquipmentNotFoundException;
import com.llp.amm.exception.SectorNotFoundException;
import com.llp.amm.repository.DepartmentRepository;
import com.llp.amm.repository.EquipmentRepository;
import com.llp.amm.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SectorRepository sectorRepository;

    @Transactional
    public EquipmentDto addEquipament(EquipmentDto equipmentDto) {

        Department department = departmentRepository.findById(equipmentDto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(equipmentDto.departmentId()));

        Sector sector = sectorRepository.findById(equipmentDto.sectorId())
                .orElseThrow(() -> new SectorNotFoundException(equipmentDto.sectorId()));

        if (equipmentRepository.existsByNameAndDepartmentAndSector(equipmentDto.name(), department, sector)) {
            throw new EquipmentAlreadyExistsException(equipmentDto.tag());

        }

        Equipment equipment = equipmentDto.toEntity(department, sector);
        equipment = equipmentRepository.save(equipment);

        return EquipmentDto.toResponse(equipment);
    }

    @Transactional
    public void deleteService(Long tag) {
        Equipment equipment =  equipmentRepository.findById(tag)
                .orElseThrow(() ->new EquipmentNotFoundException(tag));

        equipmentRepository.delete(equipment);
    }


    public List<Equipment> findAllEquipment() {

        return equipmentRepository.findAll();
    }

    public Equipment findEquipmentByTag(Long tag) {
        return equipmentRepository.findById(tag)
                .orElseThrow(() ->new EquipmentNotFoundException(tag));
    }

    public Equipment updateEquipment(Long tag, EquipmentDto equipmentDto) {

        Equipment existingEquipment = equipmentRepository.findById(tag)
                .orElseThrow(() -> new EquipmentNotFoundException(tag));

        existingEquipment.setName(equipmentDto.name());

        if(equipmentDto.departmentId() != null){
            Department department = departmentRepository.findById(equipmentDto.departmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException(equipmentDto.departmentId()));
            existingEquipment.setDepartment(department);

        }

        if(equipmentDto.sectorId() != null){
            Sector sector = sectorRepository.findById((equipmentDto.sectorId()))
                    .orElseThrow(() -> new SectorNotFoundException(equipmentDto.sectorId()));
            existingEquipment.setSector(sector);
        }


        return equipmentRepository.save(existingEquipment);

    }
}