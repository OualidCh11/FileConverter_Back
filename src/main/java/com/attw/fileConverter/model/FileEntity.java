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
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String fileName;
    private String typeFile;
    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "fileEntity", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FileDetail> fileDetails;

    @ManyToOne
    @JoinColumn(name = "config_mapping_id")
    private Mapping configMapping;


}
