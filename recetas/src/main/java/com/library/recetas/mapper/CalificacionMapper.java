package com.library.recetas.mapper;

import com.library.recetas.dto.CalificacionDTO;
import com.library.recetas.model.Calificacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, RecetaMapper.class})
public interface CalificacionMapper {

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta.id", target = "recetaId")
    CalificacionDTO toDTO(Calificacion calificacion);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(target = "receta.id", source = "recetaId")
    Calificacion toEntity(CalificacionDTO calificacionDTO);
}
