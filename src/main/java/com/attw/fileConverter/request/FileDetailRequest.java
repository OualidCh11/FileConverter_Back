package com.attw.fileConverter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDetailRequest {

    private int nrLines;
    private String contentFile;
    private String statut;
}
