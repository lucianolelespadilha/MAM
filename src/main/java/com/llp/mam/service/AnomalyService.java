package com.llp.mam.service;

import com.llp.mam.dtos.AnomalyDto;
import com.llp.mam.dtos.EquipmentDto;
import com.llp.mam.entity.Anomaly;
import com.llp.mam.entity.Equipment;
import com.llp.mam.entity.User;
import com.llp.mam.exception.EquipmentNotFoundException;
import com.llp.mam.exception.UserNotFoundException;
import com.llp.mam.repository.AnomalyRepository;
import com.llp.mam.repository.EquipmentRepository;
import com.llp.mam.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AnomalyService {
    private final AnomalyRepository anomalyRepository;
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;

    public AnomalyService(AnomalyRepository anomalyRepository, UserRepository userRepository, EquipmentRepository equipmentRepository) {
        this.anomalyRepository = anomalyRepository;
        this.userRepository = userRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public AnomalyDto addAnomaly(AnomalyDto anomalyDto) {

        User user = userRepository.findById(anomalyDto.userId())
                .orElseThrow(() -> new UserNotFoundException(anomalyDto.userId()));

        Equipment equipment = equipmentRepository.findById(anomalyDto.equipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(anomalyDto.equipmentId()));

        Anomaly anomaly = anomalyDto.toEntity(user, equipment);

        anomaly.setDate(LocalDate.now());
        anomaly = anomalyRepository.save(anomaly);

        return AnomalyDto.fromEntity(anomaly);
    }
}
