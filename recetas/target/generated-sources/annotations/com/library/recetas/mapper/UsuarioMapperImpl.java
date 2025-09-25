package com.library.recetas.mapper;

import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T19:01:34+0200",
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

        usuarioDTO.setNombreUsuario( usuario.getNombreUsuario() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setNombreCompleto( usuario.getNombreCompleto() );
        usuarioDTO.setBio( usuario.getBio() );
        usuarioDTO.setAvatarUrl( usuario.getAvatarUrl() );
        List<String> list = usuario.getSitiosWeb();
        if ( list != null ) {
            usuarioDTO.setSitiosWeb( new ArrayList<String>( list ) );
        }
        usuarioDTO.setFechaRegistro( usuario.getFechaRegistro() );
        usuarioDTO.setId( usuario.getId() );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario( usuarioDTO.getNombreUsuario() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setNombreCompleto( usuarioDTO.getNombreCompleto() );
        usuario.setBio( usuarioDTO.getBio() );
        usuario.setAvatarUrl( usuarioDTO.getAvatarUrl() );
        List<String> list = usuarioDTO.getSitiosWeb();
        if ( list != null ) {
            usuario.setSitiosWeb( new ArrayList<String>( list ) );
        }
        usuario.setFechaRegistro( usuarioDTO.getFechaRegistro() );
        usuario.setId( usuarioDTO.getId() );

        return usuario;
    }

    @Override
    public Usuario updateEntityFromDto(UsuarioDTO usuarioDTO, Usuario usuario) {
        if ( usuarioDTO == null ) {
            return usuario;
        }

        usuario.setNombreUsuario( usuarioDTO.getNombreUsuario() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setNombreCompleto( usuarioDTO.getNombreCompleto() );
        usuario.setBio( usuarioDTO.getBio() );
        usuario.setAvatarUrl( usuarioDTO.getAvatarUrl() );
        if ( usuario.getSitiosWeb() != null ) {
            List<String> list = usuarioDTO.getSitiosWeb();
            if ( list != null ) {
                usuario.getSitiosWeb().clear();
                usuario.getSitiosWeb().addAll( list );
            }
            else {
                usuario.setSitiosWeb( null );
            }
        }
        else {
            List<String> list = usuarioDTO.getSitiosWeb();
            if ( list != null ) {
                usuario.setSitiosWeb( new ArrayList<String>( list ) );
            }
        }

        return usuario;
    }
}
