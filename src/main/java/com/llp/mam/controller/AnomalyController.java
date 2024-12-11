package com.llp.mam.controller;

import com.llp.mam.dtos.AnomalyDto;
import com.llp.mam.repository.AnomalyRepository;
import com.llp.mam.service.AnomalyService;
import com.llp.mam.service.EquipmentService;
import com.llp.mam.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnomalyService anomalyService;
    @Autowired
    private AnomalyRepository anomalyRepository;

    @PostMapping
    public ResponseEntity<AnomalyDto> addAnomaly(@RequestBody @Valid AnomalyDto anomalyDto){
        AnomalyDto savedAnomalyDto = anomalyService.addAnomaly(anomalyDto);
        return ResponseEntity.ok(savedAnomalyDto);
    }

    @GetMapping("/{anomalyId}")
    public ResponseEntity<AnomalyDto>getAnomalyById(@PathVariable Long anomalyId){
            return ResponseEntity.ok(anomalyService.getAnomalyById(anomalyId));
    }

    @GetMapping
    public ResponseEntity<List<AnomalyDto>> getAllAnomalies(){

        List<AnomalyDto> anomalies = anomalyService.findAllAnomalies();
        return ResponseEntity.ok(anomalies);

    }

    @DeleteMapping("/{anomalyId}")
    public ResponseEntity<Void>deleteAnomaly(@PathVariable Long anomalyId){
        anomalyService.deleteAnomaly(anomalyId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<AnomalyDto> updateAnomaly(@PathVariable Long id, @RequestBody @Valid AnomalyDto anomalyDto){
        AnomalyDto  updateAnomaly = anomalyService.upDateAnomaly(id, anomalyDto);
        return ResponseEntity.ok(updateAnomaly);
    }

}
