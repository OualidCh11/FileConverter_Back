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

    @PostMapping("/saveKeys-withPosition")
    public ResponseEntity<String> saveKeysWithPosition(
            @RequestParam("file") MultipartFile file,
            @RequestParam("metadata") String metadata
    ) throws IOException {
        String jsonContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        JsonUploadRequest jsonUploadRequest = new com.fasterxml.jackson.databind.ObjectMapper()
                .   readValue(metadata, JsonUploadRequest.class);

        jsonStructureService.saveJsonStructure(jsonContent, jsonUploadRequest);

        return ResponseEntity.ok("Structure JSON sauvegardée avec succès ✅");
    }
    @GetMapping("/getByDestination")
    public ResponseEntity<List<JsonStructure>> getByDestination(@RequestParam String fileDestination) {
        return ResponseEntity.ok(jsonStructureService.getByFileDestination(fileDestination));
    }

    @GetMapping("/getAllDestinations")
    public ResponseEntity<List<String>> getAllDestinations() {
        return ResponseEntity.ok(jsonStructureService.getAllFileDestinations());
    }


}
