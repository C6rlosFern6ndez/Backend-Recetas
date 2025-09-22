package com.library.recetas.dto;

import java.time.LocalDateTime;

public class UsuarioDTO {
    private Integer id;
    private String nombreUsuario;
    private String email;
    private LocalDateTime fechaRegistro;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer id, String nombreUsuario, String email, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
