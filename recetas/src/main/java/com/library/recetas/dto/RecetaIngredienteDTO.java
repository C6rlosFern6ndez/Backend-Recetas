package com.library.recetas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaIngredienteDTO {
    private Integer recetaId;
    private Integer ingredienteId;
    private String cantidad;
    private IngredienteDTO ingrediente; 
}
