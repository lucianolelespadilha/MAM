package com.llp.mam.controller;

import com.llp.mam.dtos.EquipmentDto;
import com.llp.mam.entity.Equipment;
import com.llp.mam.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<EquipmentDto> addEquipment(@RequestBody @Valid EquipmentDto equipmentDto) {
        EquipmentDto savedEquipmentDto = equipmentService.addEquipament(equipmentDto);
        return ResponseEntity.ok(savedEquipmentDto);
    }

    @DeleteMapping("/{tag}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long tag) {
        equipmentService.deleteService(tag);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDto>> getAllEquipment() {
        List<Equipment> equipment = equipmentService.findAllEquipment();
        List<EquipmentDto> equipmentDtos = equipment.stream()
                .map(EquipmentDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(equipmentDtos);
    }

    @GetMapping("/{tag}")
    public ResponseEntity<EquipmentDto> getEquipmentByTag(@PathVariable Long tag) {
        Equipment equipment = equipmentService.findEquipmentByTag(tag);
        EquipmentDto equipmentDto = EquipmentDto.fromEntity(equipment);

        return ResponseEntity.ok(equipmentDto);
    }


    @PutMapping("/{tag}")
    public ResponseEntity<EquipmentDto> updateDepartment(@PathVariable Long tag, @RequestBody @Valid EquipmentDto equipmentDto) {
        Equipment updatedEquipment = equipmentService.updateEquipment(tag, equipmentDto);
        EquipmentDto updatedEquipmentDto = EquipmentDto.fromEntity(updatedEquipment);
        return ResponseEntity.ok(updatedEquipmentDto);

    }


}
