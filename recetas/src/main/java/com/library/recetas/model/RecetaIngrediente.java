package com.library.recetas.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "receta_ingredientes")
public class RecetaIngrediente {

    @EmbeddedId
    private RecetaIngredienteId id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    @Column(length = 50)
    private String cantidad;

    public RecetaIngrediente() {
    }

    public RecetaIngrediente(Receta receta, Ingrediente ingrediente, String cantidad) {
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.id = new RecetaIngredienteId(receta.getId(), ingrediente.getId());
    }

    // Getters y Setters
    public RecetaIngredienteId getId() {
        return id;
    }

    public void setId(RecetaIngredienteId id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Embeddable
    public static class RecetaIngredienteId implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name = "receta_id")
        private Integer recetaId;

        @Column(name = "ingrediente_id")
        private Integer ingredienteId;

        public RecetaIngredienteId() {
        }

        public RecetaIngredienteId(Integer recetaId, Integer ingredienteId) {
            this.recetaId = recetaId;
            this.ingredienteId = ingredienteId;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RecetaIngredienteId that = (RecetaIngredienteId) o;
            return Objects.equals(recetaId, that.recetaId) &&
                   Objects.equals(ingredienteId, that.ingredienteId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(recetaId, ingredienteId);
        }
    }
}
