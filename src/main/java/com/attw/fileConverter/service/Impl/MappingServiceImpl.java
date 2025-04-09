package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.FileEntity;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.repository.FileRepository;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {


    private final MappingRepository mappingRepository;
    private final FileRepository fileRepository;


    @Override
    public Mapping saveMapping(MappingDTO mappingDTO) {
        FileEntity lastUploadedFile = fileRepository.findLastUploadedFile(); // Utilise la nouvelle méthode

        if (lastUploadedFile == null) {
            throw new RuntimeException("Aucun fichier valide uploadé récemment");
        }

        Mapping mapping = new Mapping();
        mapping.setFileSource(lastUploadedFile.getFileName());
        mapping.setFileDestinqtionJson(mappingDTO.getFileDestinationName());

        return mappingRepository.save(mapping);
    }


}

