package com.library.recetas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasoDTO {
    private Integer id;
    private Integer orden;
    private String descripcion;
    private Integer recetaId; 
}
