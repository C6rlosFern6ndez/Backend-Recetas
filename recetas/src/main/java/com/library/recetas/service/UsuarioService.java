package com.library.recetas.service;

import com.library.recetas.model.Usuario;
import com.library.recetas.repository.UsuarioRepository;
import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.mapper.UsuarioMapper;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

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
        // Encode the password before saving
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
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

    /**
     * Updates the avatar URL for a given user.
     * @param email The email of the user whose avatar URL needs to be updated.
     * @param avatarUrl The new avatar URL.
     */
    public void updateAvatarUrl(String email, String avatarUrl) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "email", email));
        usuario.setAvatarUrl(avatarUrl);
        usuarioRepository.save(usuario);
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
