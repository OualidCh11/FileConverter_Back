package com.attw.fileConverter.controller;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/mapping")
@RequiredArgsConstructor
public class MappingController {

    private final MappingService mappingService;

    @PostMapping("save-map")
    public ResponseEntity<?> createMapping(@RequestBody MappingDTO mappingDTO) {
        try {
            // Validate required fields
            if (mappingDTO.getFileDestinationName() == null || mappingDTO.getFileDestinationName().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File destination name is required");
            }

            Mapping savedMapping = mappingService.saveMapping(mappingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMapping);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

}
