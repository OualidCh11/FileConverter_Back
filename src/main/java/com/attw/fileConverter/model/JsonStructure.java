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
@Table(name = "json_structure")
public class JsonStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String keyPath;
    private String fileDestination;
    private LocalDateTime dateCreated;
    private String typeLine;

    @OneToMany(mappedBy = "jsonStructure")
    private List<ConfigMappingDetail> configMappingDetails;
}
