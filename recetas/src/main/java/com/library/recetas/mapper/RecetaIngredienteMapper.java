package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaIngredienteDTO;
import com.library.recetas.model.RecetaIngrediente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {IngredienteMapper.class})
public interface RecetaIngredienteMapper {

    RecetaIngredienteMapper INSTANCE = Mappers.getMapper(RecetaIngredienteMapper.class);

    @Mapping(source = "receta.id", target = "recetaId")
    @Mapping(source = "ingrediente.id", target = "ingredienteId")
    @Mapping(source = "ingrediente", target = "ingrediente")
    RecetaIngredienteDTO toDTO(RecetaIngrediente recetaIngrediente);

    @Mapping(target = "receta.id", source = "recetaId")
    @Mapping(target = "ingrediente.id", source = "ingredienteId")
    @Mapping(target = "id.recetaId", source = "recetaId")
    @Mapping(target = "id.ingredienteId", source = "ingredienteId")
    RecetaIngrediente toEntity(RecetaIngredienteDTO recetaIngredienteDTO);
}
