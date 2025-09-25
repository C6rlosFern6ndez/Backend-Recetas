package com.library.recetas.service;

import com.library.recetas.model.Paso;
import com.library.recetas.repository.PasoRepository;
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
class PasoServiceTest {

    @Mock
    private PasoRepository pasoRepository;

    @InjectMocks
    private PasoService pasoService;

    private Paso paso;

    @BeforeEach
    void setUp() {
        paso = new Paso();
        paso.setId(1);
        paso.setDescripcion("Cortar las verduras");
    }

    @Test
    void testFindAll() {
        when(pasoRepository.findAll()).thenReturn(Collections.singletonList(paso));

        List<Paso> result = pasoService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(paso.getDescripcion(), result.get(0).getDescripcion());
        verify(pasoRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(pasoRepository.findById(1)).thenReturn(Optional.of(paso));

        Optional<Paso> result = pasoService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(paso.getId(), result.get().getId());
        verify(pasoRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(pasoRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Paso> result = pasoService.findById(1);

        assertFalse(result.isPresent());
        verify(pasoRepository, times(1)).findById(1);
    }

    @Test
    void testSave_Success() {
        Paso newPaso = new Paso();
        newPaso.setDescripcion("Hervir el agua");

        Paso savedPaso = new Paso();
        savedPaso.setId(2);
        savedPaso.setDescripcion("Hervir el agua");

        when(pasoRepository.save(any(Paso.class))).thenReturn(savedPaso);

        Paso result = pasoService.save(newPaso);

        assertNotNull(result);
        assertEquals(savedPaso.getId(), result.getId());
        assertEquals(savedPaso.getDescripcion(), result.getDescripcion());
        verify(pasoRepository, times(1)).save(any(Paso.class));
    }

    @Test
    void testDeleteById() {
        doNothing().when(pasoRepository).deleteById(1);

        pasoService.deleteById(1);

        verify(pasoRepository, times(1)).deleteById(1);
    }
}
