package com.llp.mam.dtos;

import com.llp.mam.entity.Anomaly;
import com.llp.mam.entity.Equipment;
import com.llp.mam.entity.User;
import com.llp.mam.enums.DepResponsible;
import com.llp.mam.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AnomalyDto(
        @NotNull
        Long anomalyId,
        @NotNull
        Long userId,
        @NotBlank
        String userName,
        @NotNull
        Long equipmentId,
        @NotBlank
        String description,
        @NotNull
        Priority priority,
        @NotNull
        LocalDate date,
        @NotNull
        DepResponsible department

) {
    public static AnomalyDto fromEntity(Anomaly anomaly) {
        return new AnomalyDto(
                anomaly.getAnomalyId(),
                anomaly.getUser().getId(),
                anomaly.getUser().getUserName(),
                anomaly.getEquipment().getTag(),
                anomaly.getDescription(),
                anomaly.getPriority(),
                anomaly.getDate(),
                anomaly.getDepResponsible()
        );
    }

    public Anomaly toEntity(User user, Equipment equipment) {
        Anomaly anomaly= new Anomaly();
        anomaly.setUser(user);
        anomaly.setEquipment(equipment);
        anomaly.setDescription(this.description);
        anomaly.setPriority(this.priority);
        anomaly.setDate(date);
        anomaly.setDepResponsible(this.department);

        return anomaly;
    }
}
