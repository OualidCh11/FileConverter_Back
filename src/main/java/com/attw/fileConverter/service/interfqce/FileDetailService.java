package com.attw.fileConverter.service.interfqce;

import com.attw.fileConverter.model.FileDetail;
import com.attw.fileConverter.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileDetailService {

    void saveFileContent(MultipartFile file, FileEntity fileEntity) throws IOException;

}
