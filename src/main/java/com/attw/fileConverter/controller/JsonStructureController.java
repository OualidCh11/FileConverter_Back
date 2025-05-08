package com.attw.fileConverter.controller;

import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class JsonStructureController {

    private final JsonStructureRepository jsonStructureRepository;

    @GetMapping
    public ResponseEntity<List<String>> getKeys(@RequestParam String fileName) {
        List<String> keys = jsonStructureRepository.findByFileDestination(fileName)
                .stream()
                .map(JsonStructure::getKeyPath)
                .toList();
        return ResponseEntity.ok(keys);
    }


}
