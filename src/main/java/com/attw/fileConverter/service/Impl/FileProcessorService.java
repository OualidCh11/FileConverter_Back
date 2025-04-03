package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.model.FileDetail;
import com.attw.fileConverter.model.Statut;
import com.attw.fileConverter.repository.FileDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileProcessorService {

    private final FileDetailRepository fileDetailRepository;

    @Transactional
    public void processFile(Long fileId) {
        List<FileDetail> fileDetails = fileDetailRepository.findByFileEntityIdAndStatut(fileId, Statut.valueOf("AT"));

        for (FileDetail detail : fileDetails) {
            if (detail.getContentFile() == null || detail.getContentFile().trim().isEmpty()) {
                detail.setStatut(Statut.valueOf("RE")); // Rejetée
            } else {
                detail.setStatut(Statut.valueOf("TR")); // Traitée
            }
        }

        fileDetailRepository.saveAll(fileDetails);
    }
}

