package com.library.recetas.mapper;

import com.library.recetas.dto.UsuarioDTO;
import com.library.recetas.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "sitiosWeb", target = "sitiosWeb")
    @Mapping(source = "fechaRegistro", target = "fechaRegistro")
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "calificaciones", ignore = true)
    @Mapping(target = "comentarios", ignore = true)
    @Mapping(target = "contrasena", ignore = true) // Password should be handled by service and encoded
    @Mapping(target = "recetas", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", ignore = true) // UserDetails property, not directly mapped from DTO
    @Mapping(target = "username", ignore = true) // UserDetails property, not directly mapped from DTO
    @Mapping(target = "accountNonExpired", ignore = true) // UserDetails property
    @Mapping(target = "accountNonLocked", ignore = true) // UserDetails property
    @Mapping(target = "credentialsNonExpired", ignore = true) // UserDetails property
    @Mapping(target = "enabled", ignore = true) // UserDetails property
    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "sitiosWeb", target = "sitiosWeb")
    Usuario toEntity(UsuarioDTO usuarioDTO);

    @Mapping(target = "id", ignore = true) // ID should not be updated from DTO
    @Mapping(target = "fechaRegistro", ignore = true) // Registration date should not be updated
    @Mapping(target = "calificaciones", ignore = true)
    @Mapping(target = "comentarios", ignore = true)
    @Mapping(target = "contrasena", ignore = true) // Password should be handled by service and encoded
    @Mapping(target = "recetas", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "password", ignore = true) // UserDetails property
    @Mapping(target = "username", ignore = true) // UserDetails property
    @Mapping(target = "accountNonExpired", ignore = true) // UserDetails property
    @Mapping(target = "accountNonLocked", ignore = true) // UserDetails property
    @Mapping(target = "credentialsNonExpired", ignore = true) // UserDetails property
    @Mapping(target = "enabled", ignore = true) // UserDetails property
    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "nombreCompleto", target = "nombreCompleto")
    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    @Mapping(source = "sitiosWeb", target = "sitiosWeb")
    Usuario updateEntityFromDto(UsuarioDTO usuarioDTO, @MappingTarget Usuario usuario);
}
