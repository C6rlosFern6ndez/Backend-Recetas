package com.library.recetas.mapper;

import com.library.recetas.dto.CalificacionDTO;
import com.library.recetas.model.Calificacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, RecetaMapper.class})
public interface CalificacionMapper {

    CalificacionMapper INSTANCE = Mappers.getMapper(CalificacionMapper.class);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta", target = "receta")
    CalificacionDTO toDTO(Calificacion calificacion);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "receta", target = "receta")
    Calificacion toEntity(CalificacionDTO calificacionDTO);
}
