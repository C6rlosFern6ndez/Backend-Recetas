package com.library.recetas.dto;

import java.time.LocalDateTime;

public class CalificacionDTO {
    private Integer id;
    private Integer puntuacion;
    private LocalDateTime fechaCalificacion;
    private Integer recetaId; // Added to map from Receta entity
    private UsuarioDTO usuario; // Referencia al DTO del usuario

    public CalificacionDTO() {
    }

    public CalificacionDTO(Integer id, Integer puntuacion, LocalDateTime fechaCalificacion, UsuarioDTO usuario) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.fechaCalificacion = fechaCalificacion;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDateTime getFechaCalificacion() {
        return fechaCalificacion;
    }

    public void setFechaCalificacion(LocalDateTime fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public Integer getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Integer recetaId) {
        this.recetaId = recetaId;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
