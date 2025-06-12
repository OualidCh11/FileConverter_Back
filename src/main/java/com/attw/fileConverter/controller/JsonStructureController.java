package com.attw.fileConverter.controller;

import com.attw.fileConverter.dto.JsonUploadRequest;
import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/json-keys")
@RequiredArgsConstructor
public class JsonStructureController {

    private final JsonStructureService jsonStructureService;

    @PostMapping(value = "/saveKeys-withPosition",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadJsonStructure(
            @RequestPart("file") MultipartFile file,
            @RequestPart("metadata")JsonUploadRequest jsonUploadRequest ) {
        try {
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            jsonStructureService.saveJsonStructureWithPosition(content, jsonUploadRequest);
            return ResponseEntity.ok("Structure enregistrée avec succès ✅");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erreur de validation : " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erreur lecture fichier : " + e.getMessage());
        }
    }

    @GetMapping("/getByDestination")
    public ResponseEntity<List<JsonStructure>> getByFileDestination(@RequestParam String fileDestination) {
        List<JsonStructure> jsonStructureList = jsonStructureService.getByFileDestination(fileDestination);
        return ResponseEntity.ok(jsonStructureList);
    }

    @GetMapping("/getAllFileDestinations")
    public ResponseEntity<List<String>> getAllFileDestinations() {
        List<String> fileDestination = jsonStructureService.getAllFileDestinations();
        return ResponseEntity.ok(fileDestination);
    }


}
