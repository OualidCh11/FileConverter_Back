package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.ConfigMappingDetail;
import com.attw.fileConverter.model.FileDetail;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.repository.ConfigMappingRepository;
import com.attw.fileConverter.repository.FileDetailRepository;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.service.interfqce.ConfigMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigMappingImpl implements ConfigMappingService {

    private final ConfigMappingRepository configMappingRepository;
    private final MappingRepository mappingRepository;
    private final FileDetailRepository fileDetailRepository;

    @Override
    public ConfigMappingDetail saveConfigMapping(ConfigMappingDTO configMappingDTO) {
        Mapping configMapping = mappingRepository.findTopByOrderByLocalDateTimeDesc();
        if (configMapping != null) {
            throw new RuntimeException("Aucun mapping trouvé");
        }

        FileDetail fileDetail = fileDetailRepository.findById(configMappingDTO.getFileDetailId()).orElseThrow(() ->
                new RuntimeException("FileDetail introuvable avec l'ID: " + configMappingDTO.getFileDetailId()));

        ConfigMappingDetail configMappingDetail = new ConfigMappingDetail();
        configMappingDetail.setNrLineFiles(configMappingDTO.getNrLineFiles());
        configMappingDetail.setKeySource(configMappingDTO.getKeySource());
        configMappingDetail.setTypeFile(configMappingDTO.getTypeFile());
        configMappingDetail.setKeyDistination(configMappingDTO.getKeyDistination());
        configMappingDetail.setValueDistination(configMappingDTO.getValueDistination());
        configMappingDetail.setStartPos(configMappingDTO.getStartPos());
        configMappingDetail.setEndPos(configMappingDTO.getEndPos());
        configMappingDetail.setConfigMapping(configMapping);
        configMappingDetail.setFileDetail(fileDetail);

        return configMappingRepository.save(configMappingDetail);
    }
}
