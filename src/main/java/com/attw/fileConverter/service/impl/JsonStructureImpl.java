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
    public void saveJsonStructureWithPosition(String jsonContent, JsonUploadRequest jsonUploadRequest) throws IOException {

        List<JsonStructure> existing = jsonStructureRepository.findByFileDestination(jsonUploadRequest.getFileDestination());
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("fileDestination existe déjà : " + jsonUploadRequest.getFileDestination());
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonContent);

        for (PositionJsonDto positionJsonDto : jsonUploadRequest.getPositionJsonDtos()) {
            JsonNode value = getByKeyPath(rootNode, positionJsonDto.getKeyPath());
            if (value == null) {
                throw new IllegalArgumentException("Clé non trouvée dans le JSON : " + positionJsonDto.getKeyPath());
            }
        }

        List<String> extractKeyJson = JsonKeyExtractor.extractJson(jsonContent);

        List<JsonStructure> jsonStructureList = extractKeyJson.stream().map(
                key -> {
                    JsonStructure jsonStructure = new JsonStructure();
                    jsonStructure.setKeyPath(key);
                    jsonStructure.setFileDestination(jsonUploadRequest.getFileDestination());
                    jsonStructure.setDateCreated(LocalDateTime.now());

                    jsonUploadRequest.getPositionJsonDtos().stream()
                            .filter(dto -> dto.getKeyPath().equalsIgnoreCase(key))
                            .findFirst()
                            .ifPresent(dto -> jsonStructure.setTypeLine(dto.getTypeLine()));

                    return jsonStructure;
                }
        ).toList();

        jsonStructureRepository.saveAll(jsonStructureList);
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
