package com.library.recetas.mapper;

import com.library.recetas.dto.PasoDTO;
import com.library.recetas.model.Paso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RecetaMapper.class})
public interface PasoMapper {

    @Mapping(source = "receta.id", target = "recetaId") // Assuming Paso has a Receta relation
    PasoDTO toDTO(Paso paso);

    @Mapping(target = "receta", expression = "java(pasoDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(pasoDTO.getRecetaId()) : null)")
    Paso toEntity(PasoDTO pasoDTO);
}
