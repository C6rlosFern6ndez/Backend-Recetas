package com.library.recetas.mapper;

import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "calificaciones", ignore = true)
    @Mapping(target = "comentarios", ignore = true)
    @Mapping(target = "contrasena", ignore = true)
    @Mapping(target = "recetas", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "accountNonExpired", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "credentialsNonExpired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
