package com.llp.amm.repository;

import com.llp.amm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserName(String userName);
    void deleteById(Long id);
}
