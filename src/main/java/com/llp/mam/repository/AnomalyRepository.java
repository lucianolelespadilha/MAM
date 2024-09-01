package com.llp.mam.repository;

import com.llp.mam.entity.Anomaly;
import com.llp.mam.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {

    boolean existsByDescriptionAndEquipmentAndDate(String description, Equipment aLong, LocalDate date);
}
