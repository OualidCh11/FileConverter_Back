package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.ConfigMapping;
import com.attw.fileConverter.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMappingRepository extends JpaRepository<ConfigMapping, Long> {

}
