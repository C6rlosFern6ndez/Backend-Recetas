package com.library.recetas.dto;

public class RecetaIngredienteDTO {
    private Integer recetaId;
    private Integer ingredienteId;
    private String cantidad;
    private IngredienteDTO ingrediente; // Para mostrar detalles del ingrediente

    public RecetaIngredienteDTO() {
    }

    public RecetaIngredienteDTO(Integer recetaId, Integer ingredienteId, String cantidad, IngredienteDTO ingrediente) {
        this.recetaId = recetaId;
        this.ingredienteId = ingredienteId;
        this.cantidad = cantidad;
        this.ingrediente = ingrediente;
    }

    // Getters y Setters
    public Integer getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Integer recetaId) {
        this.recetaId = recetaId;
    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }
}
