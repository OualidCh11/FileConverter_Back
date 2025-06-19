package com.attw.fileConverter.service.impl;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.*;
import com.attw.fileConverter.repository.ConfigMappingRepository;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.service.interfqce.ConfigMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfigMappingImpl implements ConfigMappingService {

    private final ConfigMappingRepository configMappingRepository;
    private final MappingRepository mappingRepository;
    private final JsonStructureRepository jsonStructureRepository;

    @Override
    public List<ConfigMappingDetail> saveConfigMapping(List<ConfigMappingDTO> configMappingDTOList) {
        Mapping configMapping = mappingRepository.findTopByOrderByLocalDateTimeDesc();
        if (configMapping == null) {
            throw new RuntimeException("Aucun Mapping existant !");
        }

        List<ConfigMappingDetail> savedDetails = new ArrayList<>();

        for (ConfigMappingDTO dto : configMappingDTOList) {

            JsonStructure jsonStructure = jsonStructureRepository.findByKeyPathAndFileDestination(
                    dto.getKeyDistination(), configMapping.getFileDestinqtionJson()
            ).orElseThrow(() -> new RuntimeException(
                    "Clé JSON non trouvée pour : " + dto.getKeyDistination()
            ));

            ConfigMappingDetail detail = new ConfigMappingDetail();
            detail.setKeySource(dto.getKeySource());
            detail.setKeyDistination(dto.getKeyDistination());
            detail.setStartPos(dto.getStartPos());
            detail.setEndPos(dto.getEndPos());
            detail.setConfigMapping(configMapping);
            detail.setJsonStructure(jsonStructure);


            savedDetails.add(configMappingRepository.save(detail));
        }

        return savedDetails;
    }
}
