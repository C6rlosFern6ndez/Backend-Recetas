package com.library.recetas.mapper;

import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, RecetaMapper.class})
public interface ComentarioMapper {

    @Mapping(source = "receta.id", target = "recetaId")
    ComentarioDTO toDTO(Comentario comentario);

    @Mapping(target = "receta", expression = "java(comentarioDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(comentarioDTO.getRecetaId()) : null)")
    Comentario toEntity(ComentarioDTO comentarioDTO);
}
