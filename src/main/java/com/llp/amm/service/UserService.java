package com.llp.amm.service;


import com.llp.amm.dtos.UserDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.entity.User;
import com.llp.amm.exception.DepartmentNotFoundException;
import com.llp.amm.exception.SectorNotFoundException;
import com.llp.amm.exception.UserAlreadyExistsException;
import com.llp.amm.exception.UserNotFoundException;
import com.llp.amm.repository.DepartmentRepository;
import com.llp.amm.repository.SectorRepository;
import com.llp.amm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SectorRepository sectorRepository;

    @Transactional
    public User addUser(UserDto userDto) {

        if (userRepository.existsByUserName(userDto.userName())) {
            throw new UserAlreadyExistsException(userDto.userName());
        }

        Department department = departmentRepository.findById(userDto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(userDto.departmentId()));

        Sector sector = sectorRepository.findById(userDto.sectorId())
                .orElseThrow(() -> new SectorNotFoundException(userDto.sectorId()));

        User user = userDto.toEntity(department, sector);
        return userRepository.save(user);

    }

    @Transactional
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
    }

    @Autowired
    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository, SectorRepository sectorRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.sectorRepository = sectorRepository;
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(userId));

        Department department = departmentRepository.findById(userDto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(userDto.departmentId()));

        Sector sector = sectorRepository.findById(userDto.sectorId())
                .orElseThrow(() -> new SectorNotFoundException(userDto.sectorId()));

        user.setUserName(userDto.userName());
        user.setDepartment(department);
        user.setSector(sector);
        user.setProfile(userDto.profile());

        return userRepository.save(user);
    }
}
