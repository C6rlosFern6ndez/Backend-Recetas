package com.library.recetas.mapper;

import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, RecetaMapper.class})
public interface ComentarioMapper {

    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta", target = "receta")
    ComentarioDTO toDTO(Comentario comentario);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta", target = "receta")
    Comentario toEntity(ComentarioDTO comentarioDTO);
}
