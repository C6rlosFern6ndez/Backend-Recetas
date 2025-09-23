package com.library.recetas.service;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.mapper.RecetaMapper;
import com.library.recetas.model.Receta;
import com.library.recetas.repository.RecetaRepository;
import com.library.recetas.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;

    public List<RecetaDTO> findAll() {
        return recetaRepository.findAll().stream()
                .map(recetaMapper::toDTO)
                .toList();
    }

    public RecetaDTO findById(Integer id) {
        return recetaRepository.findRecetaWithDetailsById(id)
                .map(recetaMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", "id", id));
    }

    public RecetaDTO save(RecetaDTO recetaDTO) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Receta receta = recetaMapper.toEntity(recetaDTO);
        receta.setUsuario(usuario);
        return recetaMapper.toDTO(recetaRepository.save(receta));
    }

    public RecetaDTO update(Integer id, RecetaDTO recetaDTO) {
        Receta existingReceta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", "id", id));
        
        recetaMapper.updateEntityFromDTO(recetaDTO, existingReceta);
        return recetaMapper.toDTO(recetaRepository.save(existingReceta));
    }

    public void deleteById(Integer id) {
        if (!recetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receta", "id", id);
        }
        recetaRepository.deleteById(id);
    }

    public boolean isOwner(Integer recetaId, Integer userId) {
        return recetaRepository.findById(recetaId)
                .map(receta -> receta.getUsuario().getId().equals(userId))
                .orElse(false);
    }
}
