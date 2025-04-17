package com.attw.fileConverter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappingResponse {
    private Long id;
    private String fileSource;
    private String fileDestinqtionJson;
    private String status;
    private LocalDateTime createdAt;
}
