package com.library.recetas.service;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.mapper.RecetaMapper;
import com.library.recetas.model.Receta;
import com.library.recetas.model.Usuario;
import com.library.recetas.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @Mock
    private RecetaMapper recetaMapper;

    @InjectMocks
    private RecetaService recetaService;

    private Receta receta;
    private RecetaDTO recetaDTO;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombreUsuario("testuser");

        receta = new Receta();
        receta.setId(1);
        receta.setTitulo("Tarta de Queso");
        receta.setUsuario(usuario);

        recetaDTO = new RecetaDTO();
        recetaDTO.setId(1);
        recetaDTO.setTitulo("Tarta de Queso");
    }

    @Test
    void testFindAll() {
        when(recetaRepository.findAll()).thenReturn(Collections.singletonList(receta));
        when(recetaMapper.toDTO(any(Receta.class))).thenReturn(recetaDTO);

        List<RecetaDTO> result = recetaService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(recetaRepository.findRecetaWithDetailsById(1)).thenReturn(Optional.of(receta));
        when(recetaMapper.toDTO(receta)).thenReturn(recetaDTO);

        RecetaDTO result = recetaService.findById(1);

        assertNotNull(result);
        assertEquals(recetaDTO.getId(), result.getId());
        verify(recetaRepository, times(1)).findRecetaWithDetailsById(1);
    }

    @Test
    void testFindById_NotFound() {
        when(recetaRepository.findRecetaWithDetailsById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> recetaService.findById(1));
        verify(recetaRepository, times(1)).findRecetaWithDetailsById(1);
    }

    @Test
    void testSave_Success() {
        // Setup SecurityContext
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        RecetaDTO newRecetaDTO = new RecetaDTO();
        newRecetaDTO.setTitulo("Flan de Huevo");
        
        Receta newReceta = new Receta();
        newReceta.setTitulo("Flan de Huevo");

        when(recetaMapper.toEntity(newRecetaDTO)).thenReturn(newReceta);
        when(recetaRepository.save(any(Receta.class))).thenReturn(newReceta);
        when(recetaMapper.toDTO(newReceta)).thenReturn(newRecetaDTO);

        RecetaDTO result = recetaService.save(newRecetaDTO);

        assertNotNull(result);
        
        ArgumentCaptor<Receta> recetaCaptor = ArgumentCaptor.forClass(Receta.class);
        verify(recetaRepository).save(recetaCaptor.capture());
        assertEquals(usuario.getId(), recetaCaptor.getValue().getUsuario().getId());
    }

    @Test
    void testUpdate_Success() {
        RecetaDTO updatedInfo = new RecetaDTO();
        updatedInfo.setTitulo("Tarta de Queso Cremosa");

        when(recetaRepository.findById(1)).thenReturn(Optional.of(receta));
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);
        when(recetaMapper.toDTO(receta)).thenReturn(recetaDTO);
        
        recetaDTO.setTitulo("Tarta de Queso Cremosa"); // Simulate update for returned DTO

        RecetaDTO result = recetaService.update(1, updatedInfo);

        assertNotNull(result);
        assertEquals("Tarta de Queso Cremosa", result.getTitulo());
        verify(recetaRepository, times(1)).findById(1);
        verify(recetaMapper, times(1)).updateEntityFromDTO(updatedInfo, receta);
        verify(recetaRepository, times(1)).save(receta);
    }

    @Test
    void testUpdate_NotFound() {
        RecetaDTO updatedInfo = new RecetaDTO();
        when(recetaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> recetaService.update(1, updatedInfo));
        verify(recetaRepository, never()).save(any(Receta.class));
    }

    @Test
    void testDeleteById_Success() {
        when(recetaRepository.existsById(1)).thenReturn(true);
        doNothing().when(recetaRepository).deleteById(1);

        recetaService.deleteById(1);

        verify(recetaRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteById_NotFound() {
        when(recetaRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> recetaService.deleteById(1));
        verify(recetaRepository, never()).deleteById(1);
    }

    @Test
    void testIsOwner_True() {
        when(recetaRepository.findById(1)).thenReturn(Optional.of(receta));
        
        boolean isOwner = recetaService.isOwner(1, 1);

        assertTrue(isOwner);
    }

    @Test
    void testIsOwner_False() {
        when(recetaRepository.findById(1)).thenReturn(Optional.of(receta));

        boolean isOwner = recetaService.isOwner(1, 2);

        assertFalse(isOwner);
    }

    @Test
    void testIsOwner_RecetaNotFound() {
        when(recetaRepository.findById(1)).thenReturn(Optional.empty());

        boolean isOwner = recetaService.isOwner(1, 1);

        assertFalse(isOwner);
    }
}
