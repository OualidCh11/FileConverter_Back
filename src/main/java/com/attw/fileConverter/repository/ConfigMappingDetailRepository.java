package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.ConfigMappingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMappingDetailRepository extends JpaRepository<ConfigMappingDetail, Long> {


}
