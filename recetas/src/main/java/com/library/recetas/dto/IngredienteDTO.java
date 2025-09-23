package com.library.recetas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredienteDTO {
    private Integer id;
    private String nombre;
    private Integer recetaId;
}
