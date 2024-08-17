package com.llp.mam.service;

import com.llp.mam.dtos.DepartmentDto;
import com.llp.mam.entity.Department;
import com.llp.mam.entity.Sector;
import com.llp.mam.exception.DepartmentAlreadyExistsException;
import com.llp.mam.exception.DepartmentNotFoundException;
import com.llp.mam.repository.DepartmentRepository;
import com.llp.mam.repository.SectorRepository;
import com.llp.mam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Department> findAllDepartments() {

        return departmentRepository.findAll();
    }


    @Transactional
    public Department addDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByName(departmentDto.name())) {
            throw new DepartmentAlreadyExistsException(departmentDto.name());
        }
        Department department = departmentDto.toEntity();

        return departmentRepository.save(department);

    }

    @Transactional
    public void deleteService(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        List<Sector> sectors = sectorRepository.findByDepartment(department);
        for (Sector sector : sectors) {
            userRepository.deleteAllBySector(sector);
        }

        departmentRepository.delete(department);


    }

    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

    }

    public Department updateDepartment(Long id, DepartmentDto departmentDto) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        existingDepartment.setName(departmentDto.name());
        return departmentRepository.save(existingDepartment);

    }


}