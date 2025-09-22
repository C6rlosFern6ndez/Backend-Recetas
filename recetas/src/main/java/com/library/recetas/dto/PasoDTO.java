package com.library.recetas.dto;

public class PasoDTO {
    private Integer id;
    private Integer orden;
    private String descripcion;

    public PasoDTO() {
    }

    public PasoDTO(Integer id, Integer orden, String descripcion) {
        this.id = id;
        this.orden = orden;
        this.descripcion = descripcion;
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
}
