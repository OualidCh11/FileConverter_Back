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
    public ResponseEntity<String> uploadJsonStructure(@RequestPart("file") MultipartFile file,@RequestPart("metadata")JsonUploadRequest jsonUploadRequest ) throws IOException {
        String jsonContent = new String(file.getBytes(), StandardCharsets.UTF_8);
        jsonStructureService.saveJsonStructureWithPosition(jsonContent,jsonUploadRequest);
        return ResponseEntity.ok("La structure enrregestres qvec secces");
    }

    @GetMapping("/getByDestination")
    public ResponseEntity<List<JsonStructure>> getByFileDestination(@RequestParam String fileDestination) {
        return ResponseEntity.ok(jsonStructureService.getByFileDestination(fileDestination));
    }

}
