package com.attw.fileConverter.controller;

import com.attw.fileConverter.service.interfqce.OutMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/output")
@RequiredArgsConstructor
public class OutMappingController {

//    private final OutMappingService outMappingService;
//
//    @PostMapping("/jsonFile")
//    public ResponseEntity<String> generateFileJson(){
//        try {
//            String message = outMappingService.generateOutMapping();
//            return ResponseEntity.ok(message);
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body("Erreur lors de la génération du JSON : " + e.getMessage());
//        }
//    }
}
