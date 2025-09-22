package com.library.recetas.mapper;

import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.model.Ingrediente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {

    IngredienteMapper INSTANCE = Mappers.getMapper(IngredienteMapper.class);

    IngredienteDTO toDTO(Ingrediente ingrediente);

    Ingrediente toEntity(IngredienteDTO ingredienteDTO);
}
