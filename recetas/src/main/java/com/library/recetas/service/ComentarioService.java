package com.library.recetas.service;

import com.library.recetas.model.Comentario;
import com.library.recetas.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> findById(Integer id) {
        return comentarioRepository.findById(id);
    }

    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteById(Integer id) {
        comentarioRepository.deleteById(id);
    }
}
