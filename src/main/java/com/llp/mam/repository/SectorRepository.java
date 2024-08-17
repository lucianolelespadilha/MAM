package com.llp.mam.repository;

import com.llp.mam.entity.Department;
import com.llp.mam.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

     boolean existsByName (String name);

    List<Sector> findByDepartment(Department department);

    void deleteAllByDepartment(Department department);

    Optional<Sector> findById(Long id);
}
