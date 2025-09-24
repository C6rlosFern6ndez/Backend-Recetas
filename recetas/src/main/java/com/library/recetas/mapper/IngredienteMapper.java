package com.library.recetas.mapper;

import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.model.Ingrediente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RecetaIngredienteMapper.class})
public interface IngredienteMapper {

    IngredienteDTO toDTO(Ingrediente ingrediente);

    Ingrediente toEntity(IngredienteDTO ingredienteDTO);
}
