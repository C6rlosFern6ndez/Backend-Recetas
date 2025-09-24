package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.model.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, CategoriaMapper.class, PasoMapper.class, RecetaIngredienteMapper.class, ComentarioMapper.class, CalificacionMapper.class})
public interface RecetaMapper {

    RecetaDTO toDTO(Receta receta);

    List<RecetaDTO> toDTOs(List<Receta> recetas);

    Receta toEntity(RecetaDTO recetaDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    Receta updateEntityFromDTO(RecetaDTO recetaDTO, @MappingTarget Receta receta);
}
