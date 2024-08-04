package com.llp.amm.controller;


import com.llp.amm.dto.DepartmentDto;
import com.llp.amm.dto.SectorDto;
import com.llp.amm.entity.Department;
import com.llp.amm.entity.Sector;
import com.llp.amm.service.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;


    @PostMapping
    public ResponseEntity<Sector> createSector(@RequestBody @Valid SectorDto sectorDto) {
        Sector savedSector = sectorService.addSector(sectorDto);

        return ResponseEntity.ok(savedSector);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SectorDto>> getAllSectors() {

        List<Sector> sectors = sectorService.findAllSector();
        List<SectorDto> sectorDtos = sectors.stream()
                .map(SectorDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sectorDtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDto> getSectorById(@PathVariable Long id) {
        Sector sector = sectorService.getSectorById(id);
        SectorDto sectorDto = SectorDto.fromEntity(sector);

        return ResponseEntity.ok(sectorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorDto> updateSector(@PathVariable Long id, @RequestBody @Valid SectorDto sectorDto) {
        Sector updatedSector = sectorService.updateSector(id, sectorDto);
        SectorDto updatedSectorDto = sectorDto.fromEntity(updatedSector);

        return ResponseEntity.ok(updatedSectorDto);
    }

}


