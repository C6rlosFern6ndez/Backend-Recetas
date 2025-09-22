package com.library.recetas.repository;

import com.library.recetas.model.Paso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoRepository extends JpaRepository<Paso, Integer> {
}
