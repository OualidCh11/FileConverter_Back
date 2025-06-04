package com.attw.fileConverter.controller;

import com.attw.fileConverter.dto.JsonUploadRequest;
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

    private final JsonStructureService jsonStructureService;

    @PostMapping("/get-keys")
    public ResponseEntity<String> uploadJsonStructure(@RequestParam JsonUploadRequest jsonUploadRequest) {
        try {
            jsonStructureService.saveJsonStructureWithPosition(
                    jsonUploadRequest.getJsonContent(),
                    jsonUploadRequest.getFileDestination(),
                    jsonUploadRequest.getPositionJsonDtos()

            );
            return ResponseEntity.ok("key et position enregistrees avec succes");
        }catch (Exception e){
            return ResponseEntity.status(500).body("erreur :"+e.getMessage());
        }
    }

    @GetMapping("/getByDestination")
    public ResponseEntity<List<JsonStructure>> getByFileDestination(@RequestParam String fileDestination) {
        return ResponseEntity.ok(jsonStructureService.getByFileDestination(fileDestination));
    }

}
