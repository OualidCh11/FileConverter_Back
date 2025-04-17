package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.ConfigMappingDetail;
import com.attw.fileConverter.model.FileDetail;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.repository.ConfigMappingRepository;
import com.attw.fileConverter.repository.FileDetailRepository;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.service.interfqce.ConfigMappingService;
import com.attw.fileConverter.service.interfqce.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigMappingImpl implements ConfigMappingService {

    private final ConfigMappingRepository configMappingRepository;
    private final MappingRepository mappingRepository;
    private final FileDetailRepository fileDetailRepository;

    @Override
    public ConfigMappingDetail saveConfigMapping(ConfigMappingDTO configMappingDTO) {
        return null;
    }


    //@Override
    //public ConfigMappingDetail saveConfigMapping(ConfigMappingDTO configMappingDTO) {
//
    //    Mapping lastMapping = mappingRepository.findTopByOrderByLocalDateTimeDesc();
    //    if (lastMapping != null) {
    //        throw new RuntimeException("Aucun mapping trouvé");
    //    }
//
    //    List<FileDetail> fileDetails = fileDetailRepository.findByFileEntity_FileNameAndStatut()
//
    //    return configMappingRepository.save(configMappingDetail);
    //}
}
