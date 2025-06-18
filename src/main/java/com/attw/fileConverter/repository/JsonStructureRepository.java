package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.JsonStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JsonStructureRepository extends JpaRepository<JsonStructure, Long> {

    List<JsonStructure> findByFileDestination(String fileDestination);

    @Query("SELECT DISTINCT j.fileDestination from JsonStructure j")
    List<String> findFileDestinations();

    Optional<JsonStructure> findByKeyPathAndFileDestination(String keyPath, String fileDestination);
}
