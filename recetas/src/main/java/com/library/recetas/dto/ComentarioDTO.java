package com.library.recetas.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {
    private Integer id;
    private String comentario;
    private LocalDateTime fechaComentario;
    private Integer recetaId; 
    private UsuarioDTO usuario; 
}
