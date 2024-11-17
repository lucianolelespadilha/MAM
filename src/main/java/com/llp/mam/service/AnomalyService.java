package com.llp.mam.service;

import com.llp.mam.dtos.AnomalyDto;
import com.llp.mam.entity.Anomaly;
import com.llp.mam.entity.Equipment;
import com.llp.mam.entity.User;
import com.llp.mam.exception.AnomalyNotFoundException;
import com.llp.mam.exception.DuplicateAnomalyException;
import com.llp.mam.exception.EquipmentNotFoundException;
import com.llp.mam.exception.UserNotFoundException;
import com.llp.mam.repository.AnomalyRepository;
import com.llp.mam.repository.EquipmentRepository;
import com.llp.mam.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

        LocalDate anomalyDate = anomalyDto.date() != null ? anomalyDto.date() : LocalDate.now();

        User user = userRepository.findById(anomalyDto.userId())
                .orElseThrow(() -> new UserNotFoundException(anomalyDto.userId()));

        Equipment equipment = equipmentRepository.findById(anomalyDto.equipmentId())
                .orElseThrow(() -> new EquipmentNotFoundException(anomalyDto.equipmentId()));


        boolean exists = anomalyRepository.existsByDescriptionAndEquipmentAndDate(
                anomalyDto.description().trim().toLowerCase(), equipment, anomalyDate);

        if (exists) {
            throw new DuplicateAnomalyException(anomalyDto.equipmentId());
        }

        Anomaly anomaly = anomalyDto.toEntity(user, equipment);
        anomaly.setDate(anomalyDate);
        anomaly = anomalyRepository.save(anomaly);

        return AnomalyDto.fromEntity(anomaly);
    }

    public AnomalyDto getAnomalyById(Long anomalyId){
        Optional<Anomaly> anomaly = anomalyRepository.findById(anomalyId);

        return anomaly
                .map(AnomalyDto::fromEntity)
                .orElseThrow(() -> new AnomalyNotFoundException(anomalyId));
    }

    public List<AnomalyDto> findAllAnomalies(){
        List<Anomaly> anomalies = anomalyRepository.findAll();

        return anomalies.stream()
                .map(AnomalyDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void deleteAnomaly(Long anomalyId){

        Anomaly anomaly = anomalyRepository.findById(anomalyId)
                .orElseThrow(() -> new AnomalyNotFoundException(anomalyId));

        anomalyRepository.delete(anomaly);
    }
}
