package com.llp.mam.controller;

import com.llp.mam.dtos.AnomalyDto;
import com.llp.mam.service.AnomalyService;
import com.llp.mam.service.EquipmentService;
import com.llp.mam.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnomalyService anomalyService;

    @PostMapping
    public ResponseEntity<AnomalyDto> addAnomaly(@RequestBody @Valid AnomalyDto anomalyDto){
        AnomalyDto savedAnomalyDto = anomalyService.addAnomaly(anomalyDto);
        return ResponseEntity.ok(savedAnomalyDto);
    }

}
