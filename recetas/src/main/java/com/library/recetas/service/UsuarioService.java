package com.library.recetas.service;

import com.library.recetas.model.Usuario;
import com.library.recetas.repository.UsuarioRepository;
import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.mapper.UsuarioMapper;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.exception.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getId() != null && usuarioRepository.existsById(usuarioDTO.getId())) {
            throw new DuplicateResourceException("Usuario", "id", usuarioDTO.getId());
        }
        if (usuarioRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario()).isPresent()) {
            throw new DuplicateResourceException("Usuario", "nombreUsuario", usuarioDTO.getNombreUsuario());
        }
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Usuario", "email", usuarioDTO.getEmail());
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO update(Integer id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        if (!existingUsuario.getNombreUsuario().equals(usuarioDTO.getNombreUsuario()) &&
            usuarioRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario()).isPresent()) {
            throw new DuplicateResourceException("Usuario", "nombreUsuario", usuarioDTO.getNombreUsuario());
        }
        if (!existingUsuario.getEmail().equals(usuarioDTO.getEmail()) &&
            usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Usuario", "email", usuarioDTO.getEmail());
        }

        existingUsuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        existingUsuario.setEmail(usuarioDTO.getEmail());
        // No actualizamos la fecha de registro aquí, ya que es una fecha de creación.
        // Si hubiera otros campos actualizables, se harían aquí.

        return usuarioMapper.toDTO(usuarioRepository.save(existingUsuario));
    }

    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
        usuarioRepository.deleteById(id);
    }
}
