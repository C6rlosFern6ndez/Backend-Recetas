package com.library.recetas.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecetaIngrediente> recetaIngredientes;

    public Ingrediente() {
    }

    public Ingrediente(String nombre) {
        this.nombre = nombre;
    }

    public Ingrediente(Integer id) { // Constructor añadido
        this.id = id;
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

    public Set<RecetaIngrediente> getRecetaIngredientes() {
        return recetaIngredientes;
    }

    public void setRecetaIngredientes(Set<RecetaIngrediente> recetaIngredientes) {
        this.recetaIngredientes = recetaIngredientes;
    }
}
