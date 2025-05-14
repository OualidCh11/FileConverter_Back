package com.attw.fileConverter.controller;

import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/json-keys")
@RequiredArgsConstructor
public class JsonStructureController {

    private final JsonStructureRepository jsonStructureRepository;
    private final JsonStructureService jsonStructureService;

    @GetMapping("/get-keys")
    public ResponseEntity<List<String>> getKeys(@RequestParam String fileName) {
        List<String> keys = jsonStructureRepository.findByFileDestination(fileName)
                .stream()
                .map(JsonStructure::getKeyPath)
                .toList();
        return ResponseEntity.ok(keys);
    }

    @PostMapping("/save-keys")
    public ResponseEntity<String> SaveStructureJson(@RequestBody Map<String, String> jsonStructureMap) throws JsonProcessingException {
        String json = jsonStructureMap.get("json");
        String fileJson = jsonStructureMap.get("fileJson");
        try {
            jsonStructureService.saveJsonStructureKeys(json,fileJson);
            return ResponseEntity.ok("Saved Json Structure");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Saved Json Structure failed"+e.getMessage());
        }
    }


}
