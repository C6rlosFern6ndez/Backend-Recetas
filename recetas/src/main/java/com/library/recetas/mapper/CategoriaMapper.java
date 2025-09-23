package com.library.recetas.mapper;

import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.model.Categoria;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {RecetaMapper.class})
public interface CategoriaMapper {

    // @Mapping(source = "receta.id", target = "recetaId")
    CategoriaDTO toDTO(Categoria categoria);

    // @Mapping(target = "receta", expression = "java(categoriaDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(categoriaDTO.getRecetaId()) : null)")
    Categoria toEntity(CategoriaDTO categoriaDTO);
}
