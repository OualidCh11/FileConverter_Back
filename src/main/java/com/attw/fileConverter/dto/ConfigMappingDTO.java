package com.attw.fileConverter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfigMappingDTO {

    private int nrLineFiles;
    private String keySource;
    private String typeFile;
    private String keyDistination;
    private String valueDistination;
    private int startPos;
    private int endPos;
    private Long configMappingId;
    private Long fileDetailId;
}
