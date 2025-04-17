package com.attw.fileConverter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "config_mapping")
public class Mapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileSource;
    private String fileDestinqtionJson;


    private LocalDateTime localDateTime;

    private Statut status;

    @OneToMany(mappedBy = "configMapping" , cascade = CascadeType.ALL)
    private List<FileEntity> fileEntities;

    @OneToMany(mappedBy = "configMapping" , cascade = CascadeType.ALL)
    private List<ConfigMappingDetail> configMappingDetails;
}
