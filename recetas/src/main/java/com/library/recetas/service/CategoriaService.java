package com.library.recetas.service;

import com.library.recetas.model.Categoria;
import com.library.recetas.repository.CategoriaRepository;
import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.mapper.CategoriaMapper;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getId() != null && categoriaRepository.existsById(categoriaDTO.getId())) {
            throw new DuplicateResourceException("Categoria", "id", categoriaDTO.getId());
        }
        if (categoriaRepository.findByNombre(categoriaDTO.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Categoria", "nombre", categoriaDTO.getNombre());
        }
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public CategoriaDTO update(Integer id, CategoriaDTO categoriaDTO) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));

        if (!existingCategoria.getNombre().equals(categoriaDTO.getNombre()) &&
            categoriaRepository.findByNombre(categoriaDTO.getNombre()).isPresent()) {
            throw new DuplicateResourceException("Categoria", "nombre", categoriaDTO.getNombre());
        }

        existingCategoria.setNombre(categoriaDTO.getNombre());
        return categoriaMapper.toDTO(categoriaRepository.save(existingCategoria));
    }

    public void deleteById(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria", "id", id);
        }
        categoriaRepository.deleteById(id);
    }
}
