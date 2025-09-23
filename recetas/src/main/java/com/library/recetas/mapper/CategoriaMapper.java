package com.library.recetas.mapper;

import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toDTO(Categoria categoria);

    Categoria toEntity(CategoriaDTO categoriaDTO);
}
