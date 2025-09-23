package com.library.recetas.repository;

import com.library.recetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {

    @EntityGraph(attributePaths = {"usuario", "categorias", "recetaIngredientes", "pasos", "comentarios", "calificaciones"})
    Optional<Receta> findRecetaWithDetailsById(Integer id);
}
