package com.library.recetas.service;

import com.library.recetas.model.Calificacion;
import com.library.recetas.repository.CalificacionRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalificacionServiceTest {

    @Mock
    private CalificacionRepository calificacionRepository;

    @InjectMocks
    private CalificacionService calificacionService;

    private Calificacion calificacion;

    @BeforeEach
    void setUp() {
        calificacion = new Calificacion();
        calificacion.setId(1);
        calificacion.setPuntuacion(5);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(calificacionRepository.findAll()).thenReturn(Collections.singletonList(calificacion));

        // Act
        List<Calificacion> result = calificacionService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(calificacion.getPuntuacion(), result.get(0).getPuntuacion());
        verify(calificacionRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(calificacionRepository.findById(1)).thenReturn(Optional.of(calificacion));

        // Act
        Optional<Calificacion> result = calificacionService.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(calificacion.getId(), result.get().getId());
        verify(calificacionRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(calificacionRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        Optional<Calificacion> result = calificacionService.findById(1);

        // Assert
        assertFalse(result.isPresent());
        verify(calificacionRepository, times(1)).findById(1);
    }

    @Test
    void testSave_Success() {
        // Arrange
        Calificacion newCalificacion = new Calificacion();
        newCalificacion.setPuntuacion(4);

        Calificacion savedCalificacion = new Calificacion();
        savedCalificacion.setId(2);
        savedCalificacion.setPuntuacion(4);

        when(calificacionRepository.save(any(Calificacion.class))).thenReturn(savedCalificacion);

        // Act
        Calificacion result = calificacionService.save(newCalificacion);

        // Assert
        assertNotNull(result);
        assertEquals(savedCalificacion.getId(), result.getId());
        assertEquals(savedCalificacion.getPuntuacion(), result.getPuntuacion());
        verify(calificacionRepository, times(1)).save(any(Calificacion.class));
    }

    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(calificacionRepository).deleteById(1);

        // Act
        calificacionService.deleteById(1);

        // Assert
        verify(calificacionRepository, times(1)).deleteById(1);
    }
}
