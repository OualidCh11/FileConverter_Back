package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.OutMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutMappingRepository extends JpaRepository<OutMapping , Long>{

}
