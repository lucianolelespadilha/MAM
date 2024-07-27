package com.llp.amm.controller;


import com.llp.amm.dto.SectorDto;
import com.llp.amm.entity.Sector;
import com.llp.amm.service.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
