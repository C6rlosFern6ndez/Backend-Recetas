package com.library.recetas.service;

import com.library.recetas.model.Calificacion;
import com.library.recetas.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    public List<Calificacion> findAll() {
        return calificacionRepository.findAll();
    }

    public Optional<Calificacion> findById(Integer id) {
        return calificacionRepository.findById(id);
    }

    public Calificacion save(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public void deleteById(Integer id) {
        calificacionRepository.deleteById(id);
    }
}
