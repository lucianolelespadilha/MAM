package com.llp.mam.service;

import com.llp.mam.dtos.SectorDto;
import com.llp.mam.entity.Department;
import com.llp.mam.entity.Sector;
import com.llp.mam.entity.User;
import com.llp.mam.exception.*;
import com.llp.mam.repository.DepartmentRepository;
import com.llp.mam.repository.SectorRepository;
import com.llp.mam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    public Sector addSector(SectorDto sectorDto) {

        Department department = departmentRepository.findById(sectorDto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(sectorDto.departmentId()));

        if (sectorRepository.existsByName(sectorDto.name())) {
            throw new SectorAlreadyExistsException(sectorDto.name());
        }

        Sector sector = sectorDto.toEntity(department);
        return sectorRepository.save(sector);

    }

    @Transactional
    public void deleteService(Long sectorId) {
        //First we delete all the users who register in the sector
        List<User> users = userRepository.findBySectorId(sectorId);
        for (User user : users) {
            userRepository.delete(user);
        }

        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new SectorNotFoundException(sectorId));

        sectorRepository.delete(sector);
    }

    public List<Sector> findAllSector() {

        return sectorRepository.findAll();
    }

    public Sector getSectorById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException(id));

    }

    public Sector updateSector(Long id, SectorDto sectorDto) {
        Sector existingSector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException(id));
        existingSector.setName(sectorDto.name());
        return sectorRepository.save(existingSector);
    }
}