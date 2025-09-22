package com.library.recetas.dto;

public class IngredienteDTO {
    private Integer id;
    private String nombre;

    public IngredienteDTO() {
    }

    public IngredienteDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
