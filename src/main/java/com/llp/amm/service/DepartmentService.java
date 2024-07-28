package com.llp.amm.service;

import com.llp.amm.dto.DepartmentDto;
import com.llp.amm.entity.Department;
import com.llp.amm.exception.DepartmentAlreadyExistsException;
import com.llp.amm.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;


    public Department addDepartment(DepartmentDto departmentDto) {
        if (departmentRepository.existsByName(departmentDto.name())) {
            throw new DepartmentAlreadyExistsException(departmentDto.name());
        }
        Department department = departmentDto.toEntity();

        return departmentRepository.save(department);

    }
}
