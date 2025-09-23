package com.library.recetas.service;

import com.library.recetas.model.Usuario;
import com.library.recetas.repository.UsuarioRepository;
import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.mapper.UsuarioMapper;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

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
        validateUniqueFields(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        // La contraseña debería ser codificada aquí antes de guardar
        // usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public UsuarioDTO update(Integer id, UsuarioDTO usuarioDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        validateUniqueFields(usuarioDTO, existingUsuario);

        // Mapear los campos actualizables del DTO a la entidad existente
        usuarioMapper.updateEntityFromDto(usuarioDTO, existingUsuario);

        return usuarioMapper.toDTO(usuarioRepository.save(existingUsuario));
    }

    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
        usuarioRepository.deleteById(id);
    }

    private void validateUniqueFields(UsuarioDTO usuarioDTO, Usuario existingUsuario) {
        if (!existingUsuario.getNombreUsuario().equals(usuarioDTO.getNombreUsuario())) {
            usuarioRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario()).ifPresent(u -> {
                throw new DuplicateResourceException("Usuario", "nombreUsuario", usuarioDTO.getNombreUsuario());
            });
        }
        if (!existingUsuario.getEmail().equals(usuarioDTO.getEmail())) {
            usuarioRepository.findByEmail(usuarioDTO.getEmail()).ifPresent(u -> {
                throw new DuplicateResourceException("Usuario", "email", usuarioDTO.getEmail());
            });
        }
    }

    private void validateUniqueFields(UsuarioDTO usuarioDTO) {
        usuarioRepository.findByNombreUsuario(usuarioDTO.getNombreUsuario()).ifPresent(u -> {
            throw new DuplicateResourceException("Usuario", "nombreUsuario", usuarioDTO.getNombreUsuario());
        });
        usuarioRepository.findByEmail(usuarioDTO.getEmail()).ifPresent(u -> {
            throw new DuplicateResourceException("Usuario", "email", usuarioDTO.getEmail());
        });
    }
}
