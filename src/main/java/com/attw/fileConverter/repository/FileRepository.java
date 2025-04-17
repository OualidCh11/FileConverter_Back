package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {

    List<FileEntity> findByFileName(String fileName);

    //@Query("SELECT f FROM FileEntity f WHERE f.localDateTime IS NOT NULL ORDER BY f.localDateTime DESC")
    //List<FileEntity> findLastUploadedFileLimited();

    FileEntity findTopByLocalDateTimeIsNotNullOrderByLocalDateTimeDesc();



}
