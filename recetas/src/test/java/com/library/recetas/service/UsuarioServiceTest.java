package com.library.recetas.service;

import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.mapper.UsuarioMapper;
import com.library.recetas.model.Usuario;
import com.library.recetas.repository.UsuarioRepository;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombreUsuario("testuser");
        usuario.setEmail("test@example.com");

        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1);
        usuarioDTO.setNombreUsuario("testuser");
        usuarioDTO.setEmail("test@example.com");
    }

    @Test
    void testFindAll() {
        // Arrange
        when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(usuario));
        when(usuarioMapper.toDTO(any(Usuario.class))).thenReturn(usuarioDTO);

        // Act
        List<UsuarioDTO> result = usuarioService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(usuarioDTO.getNombreUsuario(), result.get(0).getNombreUsuario());
        verify(usuarioRepository, times(1)).findAll();
        verify(usuarioMapper, times(1)).toDTO(any(Usuario.class));
    }

    @Test
    void testFindById_Success() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(any(Usuario.class))).thenReturn(usuarioDTO);

        // Act
        UsuarioDTO result = usuarioService.findById(1);

        // Assert
        assertNotNull(result);
        assertEquals(usuarioDTO.getId(), result.getId());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testFindById_NotFound() {
        // Arrange
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioService.findById(1);
        });
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testSave_Success() {
        // Arrange
        UsuarioDTO newUserDTO = new UsuarioDTO();
        newUserDTO.setNombreUsuario("newuser");
        newUserDTO.setEmail("new@example.com");

        Usuario newUser = new Usuario();
        newUser.setId(2); // Assuming ID is assigned upon save
        newUser.setNombreUsuario("newuser");
        newUser.setEmail("new@example.com");

        UsuarioDTO savedUserDTO = new UsuarioDTO();
        savedUserDTO.setId(2);
        savedUserDTO.setNombreUsuario("newuser");
        savedUserDTO.setEmail("new@example.com");

        when(usuarioMapper.toEntity(any(UsuarioDTO.class))).thenReturn(newUser);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(newUser);
        when(usuarioMapper.toDTO(any(Usuario.class))).thenReturn(savedUserDTO);

        // Act
        UsuarioDTO result = usuarioService.save(newUserDTO);

        // Assert
        assertNotNull(result);
        assertEquals(savedUserDTO.getId(), result.getId());
        assertEquals(savedUserDTO.getNombreUsuario(), result.getNombreUsuario());
        verify(usuarioMapper, times(1)).toEntity(newUserDTO);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(usuarioMapper, times(1)).toDTO(newUser);
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        UsuarioDTO updatedUserDTO = new UsuarioDTO();
        updatedUserDTO.setId(1);
        updatedUserDTO.setNombreUsuario("updateduser");
        updatedUserDTO.setEmail("updated@example.com");

        Usuario updatedUser = new Usuario();
        updatedUser.setId(1);
        updatedUser.setNombreUsuario("updateduser");
        updatedUser.setEmail("updated@example.com");

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toEntity(any(UsuarioDTO.class))).thenReturn(updatedUser);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(updatedUser);
        when(usuarioMapper.toDTO(any(Usuario.class))).thenReturn(updatedUserDTO);

        // Act
        UsuarioDTO result = usuarioService.update(1, updatedUserDTO);

        // Assert
        assertNotNull(result);
        assertEquals(updatedUserDTO.getId(), result.getId());
        assertEquals(updatedUserDTO.getNombreUsuario(), result.getNombreUsuario());
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioMapper, times(1)).toEntity(updatedUserDTO);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(usuarioMapper, times(1)).toDTO(updatedUser);
    }

    @Test
    void testUpdate_NotFound() {
        // Arrange
        UsuarioDTO updatedUserDTO = new UsuarioDTO();
        updatedUserDTO.setId(1);
        updatedUserDTO.setNombreUsuario("updateduser");
        updatedUserDTO.setEmail("updated@example.com");

        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioService.update(1, updatedUserDTO);
        });
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioMapper, never()).toEntity(any(UsuarioDTO.class));
        verify(usuarioRepository, never()).save(any(Usuario.class));
        verify(usuarioMapper, never()).toDTO(any(Usuario.class));
    }

}
