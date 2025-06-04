package com.attw.fileConverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonStructureDto {

    private String keyPath;
    private String fileDestination;
    private int start_position;
    private int end_position;
}
