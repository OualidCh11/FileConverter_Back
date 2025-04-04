package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.ConfigMappingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigMappingRepository extends JpaRepository<ConfigMappingDetail, Long> {


}
