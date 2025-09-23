package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.model.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, CategoriaMapper.class, PasoMapper.class, RecetaIngredienteMapper.class})
public interface RecetaMapper {

    RecetaDTO toDTO(Receta receta);

    List<RecetaDTO> toDTOs(List<Receta> recetas);

    Receta toEntity(RecetaDTO recetaDTO);

    @Mapping(target = "recetaIngredientes", ignore = true) // This might need adjustment based on actual DTO/Entity fields
    void updateEntityFromDTO(RecetaDTO recetaDTO, @MappingTarget Receta receta);
}
