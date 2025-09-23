package com.library.recetas.mapper;

import com.library.recetas.dto.CalificacionDTO;
import com.library.recetas.model.Calificacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, RecetaMapper.class})
public interface CalificacionMapper {

    @Mapping(source = "receta.id", target = "recetaId")
    CalificacionDTO toDTO(Calificacion calificacion);

    @Mapping(target = "receta", expression = "java(calificacionDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(calificacionDTO.getRecetaId()) : null)")
    Calificacion toEntity(CalificacionDTO calificacionDTO);
}
