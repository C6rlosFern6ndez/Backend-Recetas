package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.model.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, CategoriaMapper.class, PasoMapper.class, RecetaIngredienteMapper.class})
public interface RecetaMapper {

    RecetaMapper INSTANCE = Mappers.getMapper(RecetaMapper.class);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "categorias", target = "categorias")
    @Mapping(source = "pasos", target = "pasos")
    @Mapping(source = "recetaIngredientes", target = "ingredientes")
    RecetaDTO toDTO(Receta receta);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "categorias", target = "categorias")
    @Mapping(source = "pasos", target = "pasos")
    @Mapping(source = "ingredientes", target = "recetaIngredientes")
    Receta toEntity(RecetaDTO recetaDTO);
}
