package com.library.recetas.service;

import com.library.recetas.model.Ingrediente;
import com.library.recetas.repository.IngredienteRepository;
import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.mapper.IngredienteMapper;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private IngredienteMapper ingredienteMapper;

    public List<IngredienteDTO> findAll() {
        return ingredienteRepository.findAll().stream()
                .map(ingredienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IngredienteDTO findById(Integer id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente", "id", id));
        return ingredienteMapper.toDTO(ingrediente);
    }

    public IngredienteDTO save(IngredienteDTO ingredienteDTO) {
        if (ingredienteDTO.getId() != null && ingredienteRepository.existsById(ingredienteDTO.getId())) {
            throw new DuplicateResourceException("Ingrediente", "id", ingredienteDTO.getId());
        }
        if (ingredienteRepository.findByNombre(ingredienteDTO.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Ingrediente", "nombre", ingredienteDTO.getNombre());
        }
        Ingrediente ingrediente = ingredienteMapper.toEntity(ingredienteDTO);
        return ingredienteMapper.toDTO(ingredienteRepository.save(ingrediente));
    }

    public IngredienteDTO update(Integer id, IngredienteDTO ingredienteDTO) {
        Ingrediente existingIngrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente", "id", id));

        if (!existingIngrediente.getNombre().equals(ingredienteDTO.getNombre()) &&
            ingredienteRepository.findByNombre(ingredienteDTO.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Ingrediente", "nombre", ingredienteDTO.getNombre());
        }

        existingIngrediente.setNombre(ingredienteDTO.getNombre());
        return ingredienteMapper.toDTO(ingredienteRepository.save(existingIngrediente));
    }

    public void deleteById(Integer id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingrediente", "id", id);
        }
        ingredienteRepository.deleteById(id);
    }
}
