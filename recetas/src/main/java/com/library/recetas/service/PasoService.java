package com.library.recetas.service;

import com.library.recetas.model.Paso;
import com.library.recetas.repository.PasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasoService {

    @Autowired
    private PasoRepository pasoRepository;

    public List<Paso> findAll() {
        return pasoRepository.findAll();
    }

    public Optional<Paso> findById(Integer id) {
        return pasoRepository.findById(id);
    }

    public Paso save(Paso paso) {
        return pasoRepository.save(paso);
    }

    public void deleteById(Integer id) {
        pasoRepository.deleteById(id);
    }
}
