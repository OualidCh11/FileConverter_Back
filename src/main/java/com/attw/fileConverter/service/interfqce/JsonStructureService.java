package com.attw.fileConverter.service.interfqce;


import java.io.IOException;

public interface JsonStructureService {

    void saveJsonStructureKeys(String json , String fileDestination) throws IOException;
    void saveJsonStructureKeysFromFile(String filePath,String fileJsonName)throws IOException;


}
