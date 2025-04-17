package com.attw.fileConverter.repository;

import com.attw.fileConverter.model.FileDetail;
import com.attw.fileConverter.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDetailRepository extends JpaRepository<FileDetail, Long> {
    List<FileDetail> findByFileEntityIdAndStatut(Long fileEntity_id, Statut statut);
    List<FileDetail> findByFileEntity_FileNameAndStatut(String fileName, Statut statut);

    List<FileDetail> findByFileEntityId(Long fileEntity_id);
}
