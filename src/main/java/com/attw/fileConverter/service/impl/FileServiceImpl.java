package com.attw.fileConverter.service.impl;

import com.attw.fileConverter.model.FileEntity;
import com.attw.fileConverter.repository.FileRepository;
import com.attw.fileConverter.service.interfqce.FileDetailService;
import com.attw.fileConverter.service.interfqce.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final FileDetailService fileDetailService;
    private final FileProcessorService fileProcessorService;

    @Override
    public void saveFileMetadataAndProcess(MultipartFile file) throws IOException {

        List<FileEntity> existingFiles = fileRepository.findByFileName(file.getOriginalFilename());

        if (!existingFiles.isEmpty()) {
            throw new RuntimeException("Un fichier avec ce nom existe déjà !");
        }


        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setTypeFile("PLAT");
        fileEntity.setLocalDateTime(LocalDateTime.now());
 
        FileEntity savedFile = fileRepository.save(fileEntity);

        fileDetailService.saveFileContent(file, savedFile);

        fileProcessorService.processFile(savedFile.getId());
    }
}



