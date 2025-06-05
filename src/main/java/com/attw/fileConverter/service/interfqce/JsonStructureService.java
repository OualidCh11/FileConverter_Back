package com.attw.fileConverter.service.interfqce;


import com.attw.fileConverter.dto.JsonUploadRequest;
import com.attw.fileConverter.dto.PositionJsonDto;
import com.attw.fileConverter.model.JsonStructure;

import java.io.IOException;
import java.util.List;

public interface JsonStructureService {

    void saveJsonStructureWithPosition(String jsonContent,JsonUploadRequest jsonUploadRequest) throws IOException;

    List<JsonStructure> getByFileDestination(String fileDestination);
}
