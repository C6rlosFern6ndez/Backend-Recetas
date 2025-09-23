package com.library.recetas.dto;

public class CategoriaDTO {
    private Integer id;
    private String nombre;
    private Integer recetaId; // Added for mapping

    public CategoriaDTO() {
    }

    public CategoriaDTO(Integer id, String nombre, Integer recetaId) {
        this.id = id;
        this.nombre = nombre;
        this.recetaId = recetaId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Integer recetaId) {
        this.recetaId = recetaId;
    }
}
