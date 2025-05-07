package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import com.attw.fileConverter.utils.JsonKeyExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@Service
public class JsonStructureImpl implements JsonStructureService {

    private final JsonStructureRepository jsonStructureRepository;

    @Override
    public void saveJsonStructureKeys(String json, String fileDestination) throws IOException {

        List<String> keys = JsonKeyExtractor.extractJson(json);

        List<JsonStructure> jsonStructureList = keys.stream().map(key ->{
            JsonStructure jsonStructures = new JsonStructure();
            jsonStructures.setKeyPath(key);
            jsonStructures.setFileDestination(fileDestination);
            jsonStructures.setDateCreated(LocalDateTime.now());
            return jsonStructures;
        }).toList();
        jsonStructureRepository.saveAll(jsonStructureList);
    }

    @Override
    public List<JsonStructure> getKeysByFileDestination(String fileDestination) {
        return jsonStructureRepository.findByFileDestination(fileDestination);
    }

}
