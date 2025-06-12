package com.attw.fileConverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionJsonDto {

    private String keyPath;
    private int start_position;
    private int end_position;
    private String typeLine;
}
