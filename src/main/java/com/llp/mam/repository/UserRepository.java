package com.llp.mam.repository;

import com.llp.mam.entity.Sector;
import com.llp.mam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);
    void deleteById(Long id);
    void deleteAllBySector(Sector sector);
    List<User> findBySectorId(Long sectorId);
}
