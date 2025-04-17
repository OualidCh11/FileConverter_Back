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
@Table(name = "config_mapping_detail")
public class ConfigMappingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nrLineFiles;
    private String keySource;
    private String typeFile;
    private String keyDistination;
    private String valueDistination;
    private int startPos;
    private int endPos;
    @ManyToOne
    @JoinColumn(name = "config_mapping_id")
    private Mapping configMapping;
    @ManyToOne
    @JoinColumn(name = "file_detail_id", nullable = false)
    private FileDetail fileDetail;
    @OneToMany(mappedBy = "configMappingDetail" , cascade = CascadeType.ALL)
    private List<OutMapping> outMappings;
}
