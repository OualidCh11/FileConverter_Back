package com.attw.fileConverter.service.impl;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.FileEntity;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.model.Statut;
import com.attw.fileConverter.repository.FileRepository;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {


    private final MappingRepository mappingRepository;
    private final FileRepository fileRepository;


    @Override
    public Mapping saveMapping(MappingDTO mappingDTO) {
        // Cherche le dernier fichier uploadé (fileSource)
        FileEntity lastUploadedFile = fileRepository.findTopByLocalDateTimeIsNotNullOrderByLocalDateTimeDesc();

        if (lastUploadedFile == null) {
            throw new RuntimeException("Aucun fichier plat uploadé récemment !");
        }

        // Crée le Mapping
        Mapping mapping = new Mapping();
        mapping.setFileSource(lastUploadedFile.getFileName());
        mapping.setFileDestinqtionJson(mappingDTO.getFileDestinqtionName());
        mapping.setStatus(Statut.TR);
        mapping.setLocalDateTime(LocalDateTime.now());

        // Sauve
        return mappingRepository.save(mapping);
    }


}

