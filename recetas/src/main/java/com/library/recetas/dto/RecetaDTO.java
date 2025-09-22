package com.library.recetas.dto;

import com.library.recetas.model.Dificultad;
import java.time.LocalDateTime;
import java.util.Set;

public class RecetaDTO {
    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer tiempoPreparacion;
    private Dificultad dificultad;
    private Integer porciones;
    private UsuarioDTO usuario; // Referencia al DTO del usuario
    private LocalDateTime fechaCreacion;
    private Set<PasoDTO> pasos;
    private Set<ComentarioDTO> comentarios;
    private Set<CalificacionDTO> calificaciones;
    private Set<RecetaIngredienteDTO> recetaIngredientes;
    private Set<CategoriaDTO> categorias;

    public RecetaDTO() {
    }

    public RecetaDTO(Integer id, String titulo, String descripcion, Integer tiempoPreparacion, Dificultad dificultad, Integer porciones, UsuarioDTO usuario, LocalDateTime fechaCreacion, Set<PasoDTO> pasos, Set<ComentarioDTO> comentarios, Set<CalificacionDTO> calificaciones, Set<RecetaIngredienteDTO> recetaIngredientes, Set<CategoriaDTO> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tiempoPreparacion = tiempoPreparacion;
        this.dificultad = dificultad;
        this.porciones = porciones;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.pasos = pasos;
        this.comentarios = comentarios;
        this.calificaciones = calificaciones;
        this.recetaIngredientes = recetaIngredientes;
        this.categorias = categorias;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<PasoDTO> getPasos() {
        return pasos;
    }

    public void setPasos(Set<PasoDTO> pasos) {
        this.pasos = pasos;
    }

    public Set<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(Set<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public Set<RecetaIngredienteDTO> getRecetaIngredientes() {
        return recetaIngredientes;
    }

    public void setRecetaIngredientes(Set<RecetaIngredienteDTO> recetaIngredientes) {
        this.recetaIngredientes = recetaIngredientes;
    }

    public Set<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
}
