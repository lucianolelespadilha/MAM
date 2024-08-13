package com.llp.amm.service;

import com.llp.amm.dtos.DepartmentDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.exception.DepartmentAlreadyExistsException;
import com.llp.amm.exception.DepartmentNotFoundException;
import com.llp.amm.repository.DepartmentRepository;
import com.llp.amm.repository.SectorRepository;
import com.llp.amm.repository.UserRepository;
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