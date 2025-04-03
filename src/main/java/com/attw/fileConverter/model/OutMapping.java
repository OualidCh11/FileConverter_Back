package com.attw.fileConverter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "out_data")
public class OutMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contentMapper;
    private LocalDateTime dateMapping;

    @ManyToOne
    @JoinColumn(name = "configM_detail_id")
    private ConfigMappingDetail configMappingDetail;


}
