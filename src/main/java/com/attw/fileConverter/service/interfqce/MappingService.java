package com.attw.fileConverter.service.interfqce;

import com.attw.fileConverter.dto.MappingDTO;
import com.attw.fileConverter.model.Mapping;

public interface MappingService {

    Mapping saveMapping(MappingDTO mappingDTO);
}
