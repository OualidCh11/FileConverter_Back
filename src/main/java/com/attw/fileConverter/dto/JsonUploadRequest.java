package com.attw.fileConverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonUploadRequest {

    private String jsonContent;
    private String fileDestination;
    private List<PositionJsonDto> positionJsonDtos;
}
