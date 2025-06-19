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

    @Override
    public Mapping saveMapping(MappingDTO mappingDTO) {
        if (mappingDTO.getFileSource() == null || mappingDTO.getFileSource().isBlank()) {
            throw new IllegalArgumentException("fileSource est obligatoire !");
        }
        if (mappingDTO.getFileDestinqtionName() == null || mappingDTO.getFileDestinqtionName().isBlank()) {
            throw new IllegalArgumentException("fileDestinqtionName est obligatoire !");
        }

        Mapping mapping = new Mapping();
        mapping.setFileSource(mappingDTO.getFileSource());
        mapping.setFileDestinqtionJson(mappingDTO.getFileDestinqtionName());
        mapping.setStatus(Statut.TR);
        mapping.setLocalDateTime(LocalDateTime.now());

        return mappingRepository.save(mapping);
    }


}

