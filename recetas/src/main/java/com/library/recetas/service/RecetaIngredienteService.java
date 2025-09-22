package com.library.recetas.service;

import com.library.recetas.model.RecetaIngrediente;
import com.library.recetas.model.RecetaIngrediente.RecetaIngredienteId;
import com.library.recetas.repository.RecetaIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaIngredienteService {

    @Autowired
    private RecetaIngredienteRepository recetaIngredienteRepository;

    public List<RecetaIngrediente> findAll() {
        return recetaIngredienteRepository.findAll();
    }

    public Optional<RecetaIngrediente> findById(RecetaIngredienteId id) {
        return recetaIngredienteRepository.findById(id);
    }

    public RecetaIngrediente save(RecetaIngrediente recetaIngrediente) {
        return recetaIngredienteRepository.save(recetaIngrediente);
    }

    public void deleteById(RecetaIngredienteId id) {
        recetaIngredienteRepository.deleteById(id);
    }
}
