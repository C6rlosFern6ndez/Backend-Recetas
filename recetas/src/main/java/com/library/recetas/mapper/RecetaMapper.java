package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.model.Receta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, CategoriaMapper.class, PasoMapper.class, RecetaIngredienteMapper.class})
public interface RecetaMapper {

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "categorias", target = "categorias")
    @Mapping(source = "pasos", target = "pasos")
    @Mapping(source = "recetaIngredientes", target = "recetaIngredientes")
    @Mapping(source = "comentarios", target = "comentarios")
    @Mapping(source = "calificaciones", target = "calificaciones")
    RecetaDTO toDTO(Receta receta);

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "categorias", target = "categorias")
    @Mapping(source = "pasos", target = "pasos")
    @Mapping(target = "recetaIngredientes", source = "recetaIngredientes")
    Receta toEntity(RecetaDTO recetaDTO);
}
