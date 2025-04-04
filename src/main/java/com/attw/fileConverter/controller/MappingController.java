package com.attw.fileConverter.controller;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mapping")
@RequiredArgsConstructor
public class MappingController {

    private final MappingService mappingService;

    @PostMapping("/savemap")
    public ResponseEntity<Mapping> saveMapping(@RequestBody MappingDTO mappingDTO) {
        Mapping savedMapping = mappingService.saveMapping(mappingDTO);
        return ResponseEntity.ok(savedMapping);
    }

}
