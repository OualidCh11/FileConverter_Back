package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingRepository extends JpaRepository<Mapping, Long> {

    Mapping findTopByOrderByLocalDateTimeDesc();

}
