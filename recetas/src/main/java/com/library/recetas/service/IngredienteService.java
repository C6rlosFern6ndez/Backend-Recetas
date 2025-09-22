package com.library.recetas.service;

import com.library.recetas.model.Ingrediente;
import com.library.recetas.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> findAll() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> findById(Integer id) {
        return ingredienteRepository.findById(id);
    }

    public Ingrediente save(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public void deleteById(Integer id) {
        ingredienteRepository.deleteById(id);
    }
}
