package com.library.recetas.service;

import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.exception.DuplicateResourceException;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.mapper.IngredienteMapper;
import com.library.recetas.model.Ingrediente;
import com.library.recetas.repository.IngredienteRepository;
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
class IngredienteServiceTest {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @Mock
    private IngredienteMapper ingredienteMapper;

    @InjectMocks
    private IngredienteService ingredienteService;

    private Ingrediente ingrediente;
    private IngredienteDTO ingredienteDTO;

    @BeforeEach
    void setUp() {
        ingrediente = new Ingrediente();
        ingrediente.setId(1);
        ingrediente.setNombre("Harina");

        ingredienteDTO = new IngredienteDTO();
        ingredienteDTO.setId(1);
        ingredienteDTO.setNombre("Harina");
    }

    @Test
    void testFindAll() {
        when(ingredienteRepository.findAll()).thenReturn(Collections.singletonList(ingrediente));
        when(ingredienteMapper.toDTO(any(Ingrediente.class))).thenReturn(ingredienteDTO);

        List<IngredienteDTO> result = ingredienteService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(ingredienteDTO.getNombre(), result.get(0).getNombre());
        verify(ingredienteRepository, times(1)).findAll();
        verify(ingredienteMapper, times(1)).toDTO(any(Ingrediente.class));
    }

    @Test
    void testFindById_Success() {
        when(ingredienteRepository.findById(1)).thenReturn(Optional.of(ingrediente));
        when(ingredienteMapper.toDTO(ingrediente)).thenReturn(ingredienteDTO);

        IngredienteDTO result = ingredienteService.findById(1);

        assertNotNull(result);
        assertEquals(ingredienteDTO.getId(), result.getId());
        verify(ingredienteRepository, times(1)).findById(1);
        verify(ingredienteMapper, times(1)).toDTO(ingrediente);
    }

    @Test
    void testFindById_NotFound() {
        when(ingredienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ingredienteService.findById(1));
        verify(ingredienteRepository, times(1)).findById(1);
    }

    @Test
    void testSave_Success() {
        IngredienteDTO newIngredienteDTO = new IngredienteDTO();
        newIngredienteDTO.setNombre("Azúcar");

        Ingrediente newIngrediente = new Ingrediente();
        newIngrediente.setNombre("Azúcar");

        Ingrediente savedIngrediente = new Ingrediente();
        savedIngrediente.setId(2);
        savedIngrediente.setNombre("Azúcar");
        
        IngredienteDTO savedIngredienteDTO = new IngredienteDTO();
        savedIngredienteDTO.setId(2);
        savedIngredienteDTO.setNombre("Azúcar");

        when(ingredienteRepository.findByNombre("Azúcar")).thenReturn(Optional.empty());
        when(ingredienteMapper.toEntity(newIngredienteDTO)).thenReturn(newIngrediente);
        when(ingredienteRepository.save(any(Ingrediente.class))).thenReturn(savedIngrediente);
        when(ingredienteMapper.toDTO(savedIngrediente)).thenReturn(savedIngredienteDTO);

        IngredienteDTO result = ingredienteService.save(newIngredienteDTO);

        assertNotNull(result);
        assertEquals(savedIngredienteDTO.getId(), result.getId());
        verify(ingredienteRepository, times(1)).save(any(Ingrediente.class));
    }

    @Test
    void testSave_DuplicateName() {
        when(ingredienteRepository.findByNombre("Harina")).thenReturn(Optional.of(ingrediente));

        assertThrows(DuplicateResourceException.class, () -> ingredienteService.save(ingredienteDTO));
        verify(ingredienteRepository, never()).save(any(Ingrediente.class));
    }

    @Test
    void testUpdate_Success() {
        IngredienteDTO updatedInfo = new IngredienteDTO();
        updatedInfo.setNombre("Harina de trigo");

        Ingrediente updatedIngrediente = new Ingrediente();
        updatedIngrediente.setId(1);
        updatedIngrediente.setNombre("Harina de trigo");

        IngredienteDTO resultDTO = new IngredienteDTO();
        resultDTO.setId(1);
        resultDTO.setNombre("Harina de trigo");

        when(ingredienteRepository.findById(1)).thenReturn(Optional.of(ingrediente));
        when(ingredienteRepository.findByNombre("Harina de trigo")).thenReturn(Optional.empty());
        when(ingredienteRepository.save(any(Ingrediente.class))).thenReturn(updatedIngrediente);
        when(ingredienteMapper.toDTO(updatedIngrediente)).thenReturn(resultDTO);

        IngredienteDTO result = ingredienteService.update(1, updatedInfo);

        assertNotNull(result);
        assertEquals("Harina de trigo", result.getNombre());
        verify(ingredienteRepository, times(1)).findById(1);
        verify(ingredienteRepository, times(1)).save(any(Ingrediente.class));
    }

    @Test
    void testUpdate_NotFound() {
        IngredienteDTO updatedInfo = new IngredienteDTO();
        updatedInfo.setNombre("Harina de trigo");

        when(ingredienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ingredienteService.update(1, updatedInfo));
        verify(ingredienteRepository, never()).save(any(Ingrediente.class));
    }

    @Test
    void testUpdate_DuplicateName() {
        IngredienteDTO updatedInfo = new IngredienteDTO();
        updatedInfo.setNombre("Azúcar");

        Ingrediente existingIngredienteWithSameName = new Ingrediente();
        existingIngredienteWithSameName.setId(2);
        existingIngredienteWithSameName.setNombre("Azúcar");

        when(ingredienteRepository.findById(1)).thenReturn(Optional.of(ingrediente));
        when(ingredienteRepository.findByNombre("Azúcar")).thenReturn(Optional.of(existingIngredienteWithSameName));

        assertThrows(DuplicateResourceException.class, () -> ingredienteService.update(1, updatedInfo));
        verify(ingredienteRepository, never()).save(any(Ingrediente.class));
    }

    @Test
    void testDeleteById_Success() {
        when(ingredienteRepository.existsById(1)).thenReturn(true);
        doNothing().when(ingredienteRepository).deleteById(1);

        ingredienteService.deleteById(1);

        verify(ingredienteRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_NotFound() {
        when(ingredienteRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> ingredienteService.deleteById(1));
        verify(ingredienteRepository, never()).deleteById(1);
    }
}
