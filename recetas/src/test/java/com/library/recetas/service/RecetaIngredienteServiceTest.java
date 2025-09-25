package com.library.recetas.service;

import com.library.recetas.model.Ingrediente;
import com.library.recetas.model.Receta;
import com.library.recetas.model.RecetaIngrediente;
import com.library.recetas.model.RecetaIngrediente.RecetaIngredienteId;
import com.library.recetas.repository.RecetaIngredienteRepository;
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
class RecetaIngredienteServiceTest {

    @Mock
    private RecetaIngredienteRepository recetaIngredienteRepository;

    @InjectMocks
    private RecetaIngredienteService recetaIngredienteService;

    private RecetaIngrediente recetaIngrediente;
    private RecetaIngredienteId recetaIngredienteId;

    @BeforeEach
    void setUp() {
        recetaIngredienteId = new RecetaIngredienteId(1, 1);

        recetaIngrediente = new RecetaIngrediente();
        recetaIngrediente.setId(recetaIngredienteId);
        recetaIngrediente.setCantidad("200g");
        
        Receta receta = new Receta();
        receta.setId(1);
        recetaIngrediente.setReceta(receta);

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(1);
        recetaIngrediente.setIngrediente(ingrediente);
    }

    @Test
    void testFindAll() {
        when(recetaIngredienteRepository.findAll()).thenReturn(Collections.singletonList(recetaIngrediente));

        List<RecetaIngrediente> result = recetaIngredienteService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(recetaIngrediente.getCantidad(), result.get(0).getCantidad());
        verify(recetaIngredienteRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(recetaIngredienteRepository.findById(recetaIngredienteId)).thenReturn(Optional.of(recetaIngrediente));

        Optional<RecetaIngrediente> result = recetaIngredienteService.findById(recetaIngredienteId);

        assertTrue(result.isPresent());
        assertEquals(recetaIngrediente.getId(), result.get().getId());
        verify(recetaIngredienteRepository, times(1)).findById(recetaIngredienteId);
    }

    @Test
    void testFindById_NotFound() {
        when(recetaIngredienteRepository.findById(recetaIngredienteId)).thenReturn(Optional.empty());

        Optional<RecetaIngrediente> result = recetaIngredienteService.findById(recetaIngredienteId);

        assertFalse(result.isPresent());
        verify(recetaIngredienteRepository, times(1)).findById(recetaIngredienteId);
    }

    @Test
    void testSave_Success() {
        RecetaIngrediente newRecetaIngrediente = new RecetaIngrediente();
        RecetaIngredienteId newId = new RecetaIngredienteId(2, 2);
        newRecetaIngrediente.setId(newId);
        newRecetaIngrediente.setCantidad("1 taza");

        when(recetaIngredienteRepository.save(any(RecetaIngrediente.class))).thenReturn(newRecetaIngrediente);

        RecetaIngrediente result = recetaIngredienteService.save(newRecetaIngrediente);

        assertNotNull(result);
        assertEquals(newRecetaIngrediente.getId(), result.getId());
        assertEquals(newRecetaIngrediente.getCantidad(), result.getCantidad());
        verify(recetaIngredienteRepository, times(1)).save(any(RecetaIngrediente.class));
    }

    @Test
    void testDeleteById() {
        doNothing().when(recetaIngredienteRepository).deleteById(recetaIngredienteId);

        recetaIngredienteService.deleteById(recetaIngredienteId);

        verify(recetaIngredienteRepository, times(1)).deleteById(recetaIngredienteId);
    }
}
