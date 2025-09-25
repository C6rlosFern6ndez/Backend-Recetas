package com.library.recetas.service;

import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.exception.DuplicateResourceException;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.mapper.CategoriaMapper;
import com.library.recetas.model.Categoria;
import com.library.recetas.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaMapper categoriaMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Postres");

        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(1);
        categoriaDTO.setNombre("Postres");
    }

    @Test
    void testFindAll() {
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));
        when(categoriaMapper.toDTO(any(Categoria.class))).thenReturn(categoriaDTO);

        List<CategoriaDTO> result = categoriaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(categoriaDTO.getNombre(), result.get(0).getNombre());
        verify(categoriaRepository, times(1)).findAll();
        verify(categoriaMapper, times(1)).toDTO(any(Categoria.class));
    }

    @Test
    void testFindById_Success() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaMapper.toDTO(categoria)).thenReturn(categoriaDTO);

        CategoriaDTO result = categoriaService.findById(1);

        assertNotNull(result);
        assertEquals(categoriaDTO.getId(), result.getId());
        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaMapper, times(1)).toDTO(categoria);
    }

    @Test
    void testFindById_NotFound() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoriaService.findById(1));
        verify(categoriaRepository, times(1)).findById(1);
    }

    @Test
    void testSave_Success() {
        CategoriaDTO newCategoriaDTO = new CategoriaDTO();
        newCategoriaDTO.setNombre("Sopas");

        Categoria newCategoria = new Categoria();
        newCategoria.setNombre("Sopas");

        Categoria savedCategoria = new Categoria();
        savedCategoria.setId(2);
        savedCategoria.setNombre("Sopas");
        
        CategoriaDTO savedCategoriaDTO = new CategoriaDTO();
        savedCategoriaDTO.setId(2);
        savedCategoriaDTO.setNombre("Sopas");

        when(categoriaRepository.findByNombre("Sopas")).thenReturn(Optional.empty());
        when(categoriaMapper.toEntity(newCategoriaDTO)).thenReturn(newCategoria);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(savedCategoria);
        when(categoriaMapper.toDTO(savedCategoria)).thenReturn(savedCategoriaDTO);

        CategoriaDTO result = categoriaService.save(newCategoriaDTO);

        assertNotNull(result);
        assertEquals(savedCategoriaDTO.getId(), result.getId());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testSave_DuplicateName() {
        when(categoriaRepository.findByNombre("Postres")).thenReturn(Optional.of(categoria));

        assertThrows(DuplicateResourceException.class, () -> categoriaService.save(categoriaDTO));
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        CategoriaDTO updatedInfo = new CategoriaDTO();
        updatedInfo.setNombre("Postres Dulces");

        Categoria updatedCategoria = new Categoria();
        updatedCategoria.setId(1);
        updatedCategoria.setNombre("Postres Dulces");

        CategoriaDTO resultDTO = new CategoriaDTO();
        resultDTO.setId(1);
        resultDTO.setNombre("Postres Dulces");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.findByNombre("Postres Dulces")).thenReturn(Optional.empty());
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(updatedCategoria);
        when(categoriaMapper.toDTO(updatedCategoria)).thenReturn(resultDTO);

        // Act
        CategoriaDTO result = categoriaService.update(1, updatedInfo);

        // Assert
        assertNotNull(result);
        assertEquals("Postres Dulces", result.getNombre());
        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaRepository, times(1)).findByNombre("Postres Dulces");
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
        verify(categoriaMapper, times(1)).toDTO(updatedCategoria);
    }

    @Test
    void testUpdate_NotFound() {
        CategoriaDTO updatedInfo = new CategoriaDTO();
        updatedInfo.setNombre("Postres Dulces");

        when(categoriaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoriaService.update(1, updatedInfo));
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void testUpdate_DuplicateName() {
        CategoriaDTO updatedInfo = new CategoriaDTO();
        updatedInfo.setNombre("Sopas");

        Categoria existingCategoriaWithSameName = new Categoria();
        existingCategoriaWithSameName.setId(2);
        existingCategoriaWithSameName.setNombre("Sopas");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.findByNombre("Sopas")).thenReturn(Optional.of(existingCategoriaWithSameName));

        assertThrows(DuplicateResourceException.class, () -> categoriaService.update(1, updatedInfo));
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void testDeleteById_Success() {
        when(categoriaRepository.existsById(1)).thenReturn(true);
        doNothing().when(categoriaRepository).deleteById(1);

        categoriaService.deleteById(1);

        verify(categoriaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_NotFound() {
        when(categoriaRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> categoriaService.deleteById(1));
        verify(categoriaRepository, never()).deleteById(1);
    }
}
