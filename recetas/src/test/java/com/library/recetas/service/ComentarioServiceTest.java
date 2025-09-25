package com.library.recetas.service;

import com.library.recetas.model.Comentario;
import com.library.recetas.repository.ComentarioRepository;
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
class ComentarioServiceTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    private Comentario comentario;

    @BeforeEach
    void setUp() {
        comentario = new Comentario();
        comentario.setId(1);
        comentario.setComentario("¡Delicioso!");
    }

    @Test
    void testFindAll() {
        when(comentarioRepository.findAll()).thenReturn(Collections.singletonList(comentario));

        List<Comentario> result = comentarioService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(comentario.getComentario(), result.get(0).getComentario());
        verify(comentarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(comentarioRepository.findById(1)).thenReturn(Optional.of(comentario));

        Optional<Comentario> result = comentarioService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(comentario.getId(), result.get().getId());
        verify(comentarioRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(comentarioRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Comentario> result = comentarioService.findById(1);

        assertFalse(result.isPresent());
        verify(comentarioRepository, times(1)).findById(1);
    }

    @Test
    void testSave_Success() {
        Comentario newComentario = new Comentario();
        newComentario.setComentario("Muy bueno");

        Comentario savedComentario = new Comentario();
        savedComentario.setId(2);
        savedComentario.setComentario("Muy bueno");

        when(comentarioRepository.save(any(Comentario.class))).thenReturn(savedComentario);

        Comentario result = comentarioService.save(newComentario);

        assertNotNull(result);
        assertEquals(savedComentario.getId(), result.getId());
        assertEquals(savedComentario.getComentario(), result.getComentario());
        verify(comentarioRepository, times(1)).save(any(Comentario.class));
    }

    @Test
    void testDeleteById() {
        doNothing().when(comentarioRepository).deleteById(1);

        comentarioService.deleteById(1);

        verify(comentarioRepository, times(1)).deleteById(1);
    }
}
