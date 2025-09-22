package com.library.recetas.repository;

import com.library.recetas.model.RecetaIngrediente;
import com.library.recetas.model.RecetaIngrediente.RecetaIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, RecetaIngredienteId> {
}
