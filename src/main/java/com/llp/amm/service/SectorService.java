package com.llp.amm.service;

import com.llp.amm.dtos.SectorDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.entity.User;
import com.llp.amm.exception.*;
import com.llp.amm.repository.DepartmentRepository;
import com.llp.amm.repository.SectorRepository;
import com.llp.amm.repository.UserRepository;
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