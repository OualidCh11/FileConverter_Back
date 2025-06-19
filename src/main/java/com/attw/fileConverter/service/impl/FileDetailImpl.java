//package com.attw.fileConverter.service.impl;
//
//import com.attw.fileConverter.model.FileDetail;
//import com.attw.fileConverter.model.FileEntity;
//import com.attw.fileConverter.model.Statut;
//import com.attw.fileConverter.repository.FileDetailRepository;
//import com.attw.fileConverter.service.interfqce.FileDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//@RequiredArgsConstructor
//public class FileDetailImpl implements FileDetailService {
//
//    private final FileDetailRepository fileDetailRepository;
//
//    @Override
//    public void saveFileContent(MultipartFile file, FileEntity fileEntity) throws IOException {
//        List<FileDetail> fileDetails = new ArrayList<>();
//        List<String> lines = new BufferedReader(new InputStreamReader(file.getInputStream()))
//                .lines()
//                .toList();
//
//        for (int i = 0; i < lines.size(); i++) {
//            String content = lines.get(i);
//
//            FileDetail detail = new FileDetail();
//            detail.setFileEntity(fileEntity);
//            detail.setNrLines(i + 1);
//            detail.setContentFile(content);
//
//            // Vérifier si la ligne est vide
//            if (content == null || content.trim().isEmpty()) {
//                detail.setStatut(Statut.valueOf("RE")); // Ligne rejetée immédiatement
//            } else {
//                detail.setStatut(Statut.valueOf("AT")); // À traiter
//            }
//
//            fileDetails.add(detail);
//        }
//
//        fileDetailRepository.saveAll(fileDetails);
//    }
//}
//
