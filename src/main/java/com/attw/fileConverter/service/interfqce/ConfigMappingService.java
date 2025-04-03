package com.attw.fileConverter.service.interfqce;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.ConfigMappingDetail;

public interface ConfigMappingService {
    ConfigMappingDetail saveConfigMapping(ConfigMappingDTO configMappingDTO);
}
