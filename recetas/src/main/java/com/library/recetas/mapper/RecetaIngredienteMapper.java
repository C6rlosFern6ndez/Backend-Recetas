package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaIngredienteDTO;
import com.library.recetas.model.RecetaIngrediente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IngredienteMapper.class, RecetaMapper.class})
public interface RecetaIngredienteMapper {

    @Mapping(source = "receta.id", target = "recetaId")
    @Mapping(source = "ingrediente.id", target = "ingredienteId")
    RecetaIngredienteDTO toDTO(RecetaIngrediente recetaIngrediente);

    @Mapping(target = "receta", expression = "java(recetaIngredienteDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(recetaIngredienteDTO.getRecetaId()) : null)")
    @Mapping(target = "ingrediente", expression = "java(recetaIngredienteDTO.getIngredienteId() != null ? new com.library.recetas.model.Ingrediente(recetaIngredienteDTO.getIngredienteId()) : null)")
    @Mapping(target = "id.recetaId", source = "recetaId")
    @Mapping(target = "id.ingredienteId", source = "ingredienteId")
    RecetaIngrediente toEntity(RecetaIngredienteDTO recetaIngredienteDTO);
}
