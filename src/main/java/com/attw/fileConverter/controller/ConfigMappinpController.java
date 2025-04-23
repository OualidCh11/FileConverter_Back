package com.attw.fileConverter.controller;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.ConfigMappingDetail;
import com.attw.fileConverter.service.interfqce.ConfigMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conf-map")
@RequiredArgsConstructor
public class ConfigMappinpController {

    private final ConfigMappingService configMappingService;

    @PostMapping("/save_confmap")
    public ResponseEntity<List<ConfigMappingDetail>> saveConfMapping(@RequestBody List<ConfigMappingDTO> configMappingDTOList) {
        List<ConfigMappingDetail> saveConfMapping = configMappingService.saveConfigMapping(configMappingDTOList);
        return ResponseEntity.ok(saveConfMapping);
    }

}
