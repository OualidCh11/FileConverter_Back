package com.attw.fileConverter.service.interfqce;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    void saveFileMetadataAndProcess(MultipartFile file) throws IOException;
}
