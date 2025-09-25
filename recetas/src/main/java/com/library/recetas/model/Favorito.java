package com.library.recetas.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Almacenamos un 'snapshot' de los detalles de la receta para conservarlos
    // incluso si la receta original es eliminada.
    // Esto se basa en la solicitud del usuario: "añada una copia de la receta para el usuario, asi si el usuario que colgo la receta decide borrarla no se perdera de la biblioteca del segundo usuario"
    @Column(name = "receta_original_id", nullable = false) // Almacenamos el ID original para referencia
    private Integer recetaOriginalId;

    @Column(name = "receta_titulo", nullable = false)
    private String recetaTitulo;

    @Column(name = "receta_descripcion", nullable = false, columnDefinition = "TEXT")
    private String recetaDescripcion;

    @Column(name = "receta_tiempo_preparacion")
    private Integer recetaTiempoPreparacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "receta_dificultad")
    private Dificultad recetaDificultad; // Asumiendo que la enum Dificultad existe

    @Column(name = "fecha_favorito", nullable = false, updatable = false)
    private LocalDateTime fechaFavorito;

    // Nota: No almacenamos todos los detalles de la receta (ingredientes, pasos, comentarios, calificaciones)
    // para evitar duplicación excesiva. Si se necesitaran, tendrían que obtenerse de la receta original
    // si aún existe, o mediante un mecanismo de snapshotting más complejo.
    // Por ahora, nos centramos en conservar la identificación básica y los detalles clave.
}
