package com.library.recetas.mapper;

import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, RecetaMapper.class})
public interface ComentarioMapper {

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta.id", target = "recetaId")
    ComentarioDTO toDTO(Comentario comentario);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(target = "receta", expression = "java(new Receta(recetaId))")
    Comentario toEntity(ComentarioDTO comentarioDTO);
}
