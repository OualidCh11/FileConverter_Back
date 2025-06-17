package com.attw.fileConverter.service.impl;

import com.attw.fileConverter.model.ConfigMappingDetail;
import com.attw.fileConverter.model.Mapping;
import com.attw.fileConverter.model.OutMapping;
import com.attw.fileConverter.repository.ConfigMappingRepository;
import com.attw.fileConverter.repository.MappingRepository;
import com.attw.fileConverter.repository.OutMappingRepository;
import com.attw.fileConverter.service.interfqce.OutMappingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class OutMappingImp implements OutMappingService {

    private final OutMappingRepository outMappingRepository;
    private final ConfigMappingRepository configMappingRepository;
    private final MappingRepository mappingRepository;
    private final ObjectMapper objectMapper;

    @Override
    public String generateOutMapping() throws Exception {

        Mapping lastMapping = mappingRepository.findTopByOrderByLocalDateTimeDesc();
        if (lastMapping == null) {
            throw new RuntimeException("Aucun mapping trouve!");
        }

        List<ConfigMappingDetail>configMappingDetails = configMappingRepository.findByConfigMapping(lastMapping);
        if (configMappingDetails.isEmpty()){
            throw new RuntimeException("Aucun configmapping trouve!");
        }

        Map<Integer ,Map<String , String>> jsonOutput = new TreeMap<>();
        for (ConfigMappingDetail configMD : configMappingDetails) {
            int lineNumber = configMD.getNrLineFiles();
            jsonOutput.computeIfAbsent(lineNumber, k -> new LinkedHashMap<>())
                    .put(configMD.getKeyDistination(),configMD.getValueDistination());
        }

        String outPutFileName = lastMapping.getFileDestinqtionJson();
        if (outPutFileName == null || outPutFileName.isEmpty()) {
            outPutFileName = "output-default.json";
        }
        String outputPath = "output/" + outPutFileName;
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, jsonOutput.values());

        OutMapping outMapping = new OutMapping();
        outMapping.setContentMapper(objectMapper.writeValueAsString(jsonOutput.values()));
        outMapping.setDateMapping(LocalDateTime.now());
        outMapping.setConfigMappingDetail(null);

        outMappingRepository.save(outMapping);

        return "Fichier JSON" + outputFile.getAbsolutePath();

    }
}
