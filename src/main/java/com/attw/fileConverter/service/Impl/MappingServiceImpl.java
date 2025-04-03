package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {


    private final MappingRepository mappingRepository;


    @Override
    public Mapping saveMapping(MappingDTO mappingDTO) {
        Mapping mapping = new Mapping();
        mapping.setFileSource(mappingDTO.getFileSourceName());
        mapping.setFileDestinqtionJson(mappingDTO.getFileDestinationName());

        return mappingRepository.save(mapping);
    }
}
