package com.library.recetas.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "tiempo_preparacion")
    private Integer tiempoPreparacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Dificultad dificultad;

    @Column
    private Integer porciones;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @JsonManagedReference
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Paso> pasos;

    @JsonManagedReference
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comentario> comentarios;

    @JsonManagedReference
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Calificacion> calificaciones;

    @JsonManagedReference
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecetaIngrediente> recetaIngredientes;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
        name = "receta_categorias",
        joinColumns = @JoinColumn(name = "receta_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias;

    public Receta() {
    }

    public Receta(Integer id) {
        this.id = id;
    }

    public Receta(String titulo, String descripcion, Integer tiempoPreparacion, Dificultad dificultad, Integer porciones, Usuario usuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tiempoPreparacion = tiempoPreparacion;
        this.dificultad = dificultad;
        this.porciones = porciones;
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(Integer tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Integer getPorciones() {
        return porciones;
    }

    public void setPorciones(Integer porciones) {
        this.porciones = porciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Paso> getPasos() {
        return pasos;
    }

    public void setPasos(Set<Paso> pasos) {
        this.pasos = pasos;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Set<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Set<RecetaIngrediente> getRecetaIngredientes() {
        return recetaIngredientes;
    }

    public void setRecetaIngredientes(Set<RecetaIngrediente> recetaIngredientes) {
        this.recetaIngredientes = recetaIngredientes;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
