package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.ConfigMappingDetail;
import com.attw.fileConverter.model.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMappingRepository extends JpaRepository<ConfigMappingDetail, Long> {


    List<ConfigMappingDetail> findByConfigMapping(Mapping lastMapping);
}
