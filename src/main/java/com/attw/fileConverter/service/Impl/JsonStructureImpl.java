package com.attw.fileConverter.service.Impl;

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

@RequiredArgsConstructor

@Service
public class JsonStructureImpl implements JsonStructureService {

    private final JsonStructureRepository jsonStructureRepository;


    @Override
    public void saveJsonStructureWithPosition(String jsonContent, String fileDestination, List<PositionJsonDto> positionJsonDtos) throws IOException {
        List<String> jsonKeys = JsonKeyExtractor.extractJson(jsonContent);

        List<JsonStructure> jsonStructureList = jsonKeys.stream().map(
                key -> {
                    PositionJsonDto positionJsonDto = positionJsonDtos.stream()
                            .filter(pos-> pos.getKeyPayh().equals(key))
                            .findFirst()
                            .orElse(null);

                    JsonStructure jsonStructure = new JsonStructure();
                    jsonStructure.setKeyPath(key);
                    jsonStructure.setFileDestination(fileDestination);
                    jsonStructure.setDateCreated(LocalDateTime.now());
                    jsonStructure.setStart_position(positionJsonDto != null? positionJsonDto.getStart_position():0);
                    jsonStructure.setEnd_position(positionJsonDto != null? positionJsonDto.getEnd_position():0);
                    return jsonStructure;
                }).toList();

        jsonStructureRepository.saveAll(jsonStructureList);
    }

    public List<JsonStructure> getByFileDestination(String fileDestination) {
        return jsonStructureRepository.findByFileDestination(fileDestination);
    }
}
