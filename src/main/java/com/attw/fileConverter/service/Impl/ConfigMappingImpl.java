package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.ConfigMappingDetail;
import com.attw.fileConverter.model.FileDetail;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.model.Statut;
import com.attw.fileConverter.repository.ConfigMappingRepository;
import com.attw.fileConverter.repository.FileDetailRepository;
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
    private final FileDetailRepository fileDetailRepository;

    @Override
    public List<ConfigMappingDetail> saveConfigMapping(List<ConfigMappingDTO> configMappingDTOList) {
        Mapping configMapping = mappingRepository.findTopByOrderByLocalDateTimeDesc();
        if (configMapping == null) {
            throw new RuntimeException("Aucun mapping trouvé");
        }

        List<FileDetail> fileDetails = fileDetailRepository.findByFileEntity_FileNameAndStatut(configMapping.
                getFileSource(), Statut.TR);

        if (fileDetails.isEmpty()){
            throw new RuntimeException("Aucun contenu de fichier trouvé");
        }

        List<ConfigMappingDetail> lastSavedConfig = new ArrayList<>();


        for (ConfigMappingDTO configMappingDTO : configMappingDTOList) {

            for (FileDetail fileDetail : fileDetails) {
                String content = fileDetail.getContentFile();
                String extractedValue = "";



                if(content != null && !content.isEmpty()){
                    if (configMappingDTO.getStartPos() > 0
                            && configMappingDTO.getEndPos() <= content.length() && configMappingDTO.getStartPos() <= configMappingDTO.getEndPos()){
                        extractedValue = content.substring(configMappingDTO.getStartPos() - 1, configMappingDTO.getEndPos()).trim();
                    }
                }else{
                    throw new RuntimeException(
                            "Positions invalides : startPos=" + configMappingDTO.getStartPos() +
                                    ", endPos=" + configMappingDTO.getEndPos() +
                                    ", longueur ligne=" + content.length()
                    );
                }

                ConfigMappingDetail configMappingDetail = new ConfigMappingDetail();
                configMappingDetail.setNrLineFiles(fileDetail.getNrLines());
                configMappingDetail.setKeySource(configMappingDTO.getKeySource());
                configMappingDetail.setTypeFile(configMappingDTO.getTypeFile());
                configMappingDetail.setKeyDistination(configMappingDTO.getKeyDistination());
                configMappingDetail.setValueDistination(extractedValue);
                configMappingDetail.setStartPos(configMappingDTO.getStartPos());
                configMappingDetail.setEndPos(configMappingDTO.getEndPos());
                configMappingDetail.setConfigMapping(configMapping);
                configMappingDetail.setFileDetail(fileDetail);

                lastSavedConfig.add(configMappingRepository.save(configMappingDetail));


            }
        }



        return lastSavedConfig;
    }
}
