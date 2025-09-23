package com.library.recetas.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionDTO {
    private Integer id;
    private Integer puntuacion;
    private LocalDateTime fechaCalificacion;
    private Integer recetaId;
    private UsuarioDTO usuario;
}
