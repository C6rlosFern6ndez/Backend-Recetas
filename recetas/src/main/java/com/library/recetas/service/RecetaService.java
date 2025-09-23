package com.library.recetas.service;

import com.library.recetas.model.Receta;
import com.library.recetas.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> findById(Integer id) {
        return recetaRepository.findRecetaWithDetailsById(id);
    }

    public Receta save(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void deleteById(Integer id) {
        recetaRepository.deleteById(id);
    }
}
