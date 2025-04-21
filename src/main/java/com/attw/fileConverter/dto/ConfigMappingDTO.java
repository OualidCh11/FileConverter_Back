package com.attw.fileConverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfigMappingDTO {

    private String keySource;
    private String typeFile;
    private String keyDistination;
    private int startPos;
    private int endPos;

}
