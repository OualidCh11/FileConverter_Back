package com.attw.fileConverter.controller;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mapping")
@RequiredArgsConstructor
public class MappingController {

    private final MappingService mappingService;

    @PostMapping("/save-map")
    public ResponseEntity<Mapping> saveMapping(@RequestBody MappingDTO mappingDTO) {
        Mapping saved = mappingService.saveMapping(mappingDTO);
        return ResponseEntity.ok(saved);
    }

}
