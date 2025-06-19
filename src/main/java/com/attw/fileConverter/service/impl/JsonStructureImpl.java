package com.attw.fileConverter.service.impl;

import com.attw.fileConverter.dto.JsonUploadRequest;
import com.attw.fileConverter.dto.PositionJsonDto;
import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import com.attw.fileConverter.utils.JsonKeyExtractor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void saveJsonStructure(String jsonContent, JsonUploadRequest jsonUploadRequest) throws IOException {

        if (!jsonStructureRepository.findByFileDestination(jsonUploadRequest.getFileDestination()).isEmpty()) {
            throw new IllegalArgumentException("fileDestination existe déjà : " + jsonUploadRequest.getFileDestination());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonContent);

        for (PositionJsonDto dto : jsonUploadRequest.getPositionJsonDtos()) {
            if (getByKeyPath(rootNode, dto.getKeyPath()) == null) {
                throw new IllegalArgumentException("Clé non trouvée dans le JSON : " + dto.getKeyPath());
            }
        }

        List<String> allKeys = JsonKeyExtractor.extractJson(jsonContent);

        List<JsonStructure> structures = allKeys.stream().map(key -> {
            JsonStructure s = new JsonStructure();
            s.setKeyPath(key);
            s.setFileDestination(jsonUploadRequest.getFileDestination());
            s.setDateCreated(LocalDateTime.now());

            jsonUploadRequest.getPositionJsonDtos().stream()
                    .filter(dto -> dto.getKeyPath().trim().equalsIgnoreCase(key.trim()))
                    .findFirst()
                    .ifPresent(dto -> s.setTypeLine(dto.getTypeLine()));

            return s;
        }).toList();

        jsonStructureRepository.saveAll(structures);
    }

    @Override
    public List<JsonStructure> getByFileDestination(String fileDestination) {
        return jsonStructureRepository.findByFileDestination(fileDestination);
    }

    @Override
    public List<String> getAllFileDestinations() {
        return jsonStructureRepository.findFileDestinations();
    }

    private JsonNode getByKeyPath(JsonNode root, String keyPath) {
        String[] parts = keyPath.split("\\.");
        JsonNode current = root;
        for (String part : parts) {
            if (current == null) return null;
            current = current.get(part);
        }
        return current;
    }
}
