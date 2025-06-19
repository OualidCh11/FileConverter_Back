//package com.attw.fileConverter.controller;
//
//import com.attw.fileConverter.model.FileDetail;
//import com.attw.fileConverter.repository.FileDetailRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/file-details")
//@RequiredArgsConstructor
//public class FileDetailController {
//
//    private final FileDetailRepository fileDetailRepository;
//
//    @GetMapping("/{fileId}")
//    public ResponseEntity<List<FileDetail>> getFileDetails(@PathVariable Long fileId) {
//        List<FileDetail> fileDetails = fileDetailRepository.findByFileEntityId(fileId);
//        return ResponseEntity.ok(fileDetails);
//    }
//}
