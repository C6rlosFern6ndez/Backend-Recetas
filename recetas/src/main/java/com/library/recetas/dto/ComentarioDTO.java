package com.library.recetas.dto;

import java.time.LocalDateTime;

public class ComentarioDTO {
    private Integer id;
    private String comentario;
    private LocalDateTime fechaComentario;
    private UsuarioDTO usuario; // Referencia al DTO del usuario

    public ComentarioDTO() {
    }

    public ComentarioDTO(Integer id, String comentario, LocalDateTime fechaComentario, UsuarioDTO usuario) {
        this.id = id;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
