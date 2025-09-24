package com.library.recetas.mapper;

import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.model.Categoria;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {RecetaMapper.class})
public interface CategoriaMapper {

    CategoriaDTO toDTO(Categoria categoria);

    Categoria toEntity(CategoriaDTO categoriaDTO);
}
