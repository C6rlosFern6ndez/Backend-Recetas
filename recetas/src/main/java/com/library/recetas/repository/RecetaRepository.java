package com.library.recetas.repository;

import com.library.recetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    @EntityGraph(attributePaths = {"usuario", "categorias", "recetaIngredientes", "pasos", "comentarios", "calificaciones"})
    Optional<Receta> findRecetaWithDetailsById(Integer id);

    /**
     * Busca recetas por nombre de categoría y tiempo máximo de preparación.
     * @param categoriaNombre El nombre de la categoría a buscar.
     * @param maxTiempoPreparacion El tiempo máximo de preparación permitido.
     * @return Una lista de recetas que coinciden con los criterios.
     */
    @Query("SELECT r FROM Receta r JOIN r.categorias c WHERE c.nombre = :categoriaNombre AND r.tiempoPreparacion <= :maxTiempoPreparacion")
    List<Receta> findByCategoriaNombreAndTiempoPreparacionLessThanEqual(String categoriaNombre, Integer maxTiempoPreparacion);

    /**
     * Busca recetas por un término que aparece en el título o la descripción.
     * @param searchTerm El término de búsqueda.
     * @return Una lista de recetas que coinciden con el término en título o descripción.
     */
    @Query("SELECT r FROM Receta r WHERE LOWER(r.titulo) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(r.descripcion) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Receta> findByTituloOrDescripcionContaining(String searchTerm);

    /**
     * Busca recetas que contengan un ingrediente específico por su nombre.
     * @param ingredienteNombre El nombre del ingrediente a buscar.
     * @return Una lista de recetas que contienen el ingrediente especificado.
     */
    @Query("SELECT r FROM Receta r JOIN r.recetaIngredientes ri JOIN ri.ingrediente i WHERE i.nombre = :ingredienteNombre")
    List<Receta> findByIngredienteNombre(String ingredienteNombre);

    /**
     * Busca recetas por dificultad.
     * @param dificultad El nivel de dificultad (ej. 'Fácil', 'Media', 'Difícil').
     * @return Una lista de recetas con la dificultad especificada.
     */
    @Query("SELECT r FROM Receta r WHERE r.dificultad = :dificultad")
    List<Receta> findByDificultad(String dificultad);

    /**
     * Busca recetas con una calificación mínima.
     * @param minPuntuacion La puntuación mínima requerida.
     * @return Una lista de recetas que tienen al menos una calificación igual o superior a minPuntuacion.
     */
    @Query("SELECT DISTINCT r FROM Receta r JOIN r.calificaciones c WHERE c.puntuacion >= :minPuntuacion")
    List<Receta> findByCalificacionesMinima(Integer minPuntuacion);
}
