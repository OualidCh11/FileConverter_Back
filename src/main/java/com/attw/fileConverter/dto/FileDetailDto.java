package com.attw.fileConverter.dto;

import com.attw.fileConverter.model.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDetailDto {

    private int nrLine;
    private String nom;
    private String prenom;
    private Integer age;
    private String role;
    private String ville;
    private Statut statut;

}
