package com.library.recetas.dto;

import com.library.recetas.model.Dificultad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer tiempoPreparacion;
    private Dificultad dificultad;
    private Integer porciones;
    private UsuarioDTO usuario;
    private LocalDateTime fechaCreacion;
    private Set<PasoDTO> pasos;
    private Set<ComentarioDTO> comentarios;
    private Set<CalificacionDTO> calificaciones;
    private Set<RecetaIngredienteDTO> recetaIngredientes;
    private Set<CategoriaDTO> categorias;
}
