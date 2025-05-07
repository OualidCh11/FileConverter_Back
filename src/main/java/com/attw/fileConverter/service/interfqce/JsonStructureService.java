package com.attw.fileConverter.service.interfqce;

import com.attw.fileConverter.model.JsonStructure;

import java.io.IOException;
import java.util.List;

public interface JsonStructureService {

    void saveJsonStructureKeys(String json , String fileDestination) throws IOException;
    List<JsonStructure> getKeysByFileDestination (String fileDestination);
}
