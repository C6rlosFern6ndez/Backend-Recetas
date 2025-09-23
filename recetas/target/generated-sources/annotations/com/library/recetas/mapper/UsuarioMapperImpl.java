package com.library.recetas.mapper;

import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T12:17:08+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setFechaRegistro( usuario.getFechaRegistro() );
        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setNombreUsuario( usuario.getNombreUsuario() );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setFechaRegistro( usuarioDTO.getFechaRegistro() );
        usuario.setId( usuarioDTO.getId() );
        usuario.setNombreUsuario( usuarioDTO.getNombreUsuario() );

        return usuario;
    }

    @Override
    public Usuario updateEntityFromDto(UsuarioDTO usuarioDTO, Usuario usuario) {
        if ( usuarioDTO == null ) {
            return usuario;
        }

        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setNombreUsuario( usuarioDTO.getNombreUsuario() );

        return usuario;
    }
}
