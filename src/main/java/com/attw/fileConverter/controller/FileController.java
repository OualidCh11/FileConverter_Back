package com.attw.fileConverter.controller;

import com.attw.fileConverter.service.interfqce.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileService.saveFileMetadataAndProcess(file);
            return ResponseEntity.ok("Fichier uploadé et traitement lancé avec succès !");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erreur lors de l'upload du fichier : " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body("Erreur : " + e.getMessage());
        }
    }

}
