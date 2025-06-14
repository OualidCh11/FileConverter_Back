package com.attw.fileConverter.service.Impl;

import com.attw.fileConverter.dto.JsonStructureDto;
import com.attw.fileConverter.dto.JsonUploadRequest;
import com.attw.fileConverter.dto.PositionJsonDto;
import com.attw.fileConverter.model.JsonStructure;
import com.attw.fileConverter.repository.JsonStructureRepository;
import com.attw.fileConverter.service.interfqce.JsonStructureService;
import com.attw.fileConverter.utils.JsonKeyExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class JsonStructureImpl implements JsonStructureService {

    private final JsonStructureRepository jsonStructureRepository;


    @Override
    public void saveJsonStructureWithPosition(String jsonContent,JsonUploadRequest jsonUploadRequest) throws IOException {

        List<JsonStructure> existing = jsonStructureRepository.findByFileDestination(jsonUploadRequest.getFileDestination());
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("fileDestination existe déjà : " + jsonUploadRequest.getFileDestination());
        }

        List<String> extractKeyJson = JsonKeyExtractor.extractJson(jsonContent);

        Set<String> normalizedKeys = extractKeyJson.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        for (PositionJsonDto positionJsonDto : jsonUploadRequest.getPositionJsonDtos()) {
            if (!normalizedKeys.contains(positionJsonDto.getKeyPath())) {
                throw new IllegalArgumentException("Clé non trouvée dans le JSON : " + positionJsonDto.getKeyPath());
            }
        }

        List<JsonStructure> jsonStructureList = jsonUploadRequest.getPositionJsonDtos().stream().map(
                dto -> {
                    JsonStructure jsonStructure = new JsonStructure();
                    jsonStructure.setKeyPath(dto.getKeyPath());
                    jsonStructure.setStart_position(dto.getStart_position());
                    jsonStructure.setEnd_position(dto.getEnd_position());
                    jsonStructure.setFileDestination(jsonUploadRequest.getFileDestination());
                    jsonStructure.setTypeLine(dto.getTypeLine());
                    jsonStructure.setDateCreated(LocalDateTime.now());
                    return jsonStructure;
                }).toList();

        jsonStructureRepository.saveAll(jsonStructureList);

    }

    public List<JsonStructure> getByFileDestination(String fileDestination) {
        return jsonStructureRepository.findByFileDestination(fileDestination);
    }

    @Override
    public List<String> getAllFileDestinations() {
        return jsonStructureRepository.findFileDestinations();
    }
}
