package com.attw.fileConverter.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file_detail")
public class FileDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nrLines;
    private String contentFile;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Enumerated(EnumType.STRING)
    private LineType lineType;
    private String codeCRE;

    @OneToMany(mappedBy = "fileDetail", cascade = CascadeType.ALL)
    private List<ConfigMappingDetail> configMappingDetails;
    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private FileEntity fileEntity;


}
