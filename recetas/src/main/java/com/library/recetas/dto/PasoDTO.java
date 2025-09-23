package com.library.recetas.dto;

public class PasoDTO {
    private Integer id;
    private Integer orden;
    private String descripcion;
    private Integer recetaId; // Added for mapping

    public PasoDTO() {
    }

    public PasoDTO(Integer id, Integer orden, String descripcion, Integer recetaId) {
        this.id = id;
        this.orden = orden;
        this.descripcion = descripcion;
        this.recetaId = recetaId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Integer recetaId) {
        this.recetaId = recetaId;
    }
}
