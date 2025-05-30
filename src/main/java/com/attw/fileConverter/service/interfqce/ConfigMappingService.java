package com.attw.fileConverter.service.interfqce;

import com.attw.fileConverter.dto.ConfigMappingDTO;
import com.attw.fileConverter.model.ConfigMappingDetail;

import java.util.List;

public interface    ConfigMappingService {
    List<ConfigMappingDetail> saveConfigMapping(List<ConfigMappingDTO> configMappingDTOList);

}
