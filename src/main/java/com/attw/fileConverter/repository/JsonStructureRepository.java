package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.JsonStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonStructureRepository extends JpaRepository<JsonStructure, Long> {
}
