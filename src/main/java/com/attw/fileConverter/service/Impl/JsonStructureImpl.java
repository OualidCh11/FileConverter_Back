package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import com.attw.fileConverter.utils.JsonKeyExtractor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@Service
public class JsonStructureImpl implements JsonStructureService {

    private final JsonStructureRepository jsonStructureRepository;

    @PostConstruct
    public void init () throws IOException{
        saveJsonStructureKeysFromFile("src/main/java/com/attw/fileConverter/structureJSON/Structure.json","StructureV1.json");
    }

    @Override
    public void saveJsonStructureKeysFromFile(String filePath, String fileJsonName) throws IOException {
        String json = Files.readString(Paths.get(filePath));
        saveJsonStructureKeys(json, fileJsonName);
    }

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

}
