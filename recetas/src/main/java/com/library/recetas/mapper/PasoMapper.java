package com.library.recetas.mapper;

import com.library.recetas.dto.PasoDTO;
import com.library.recetas.model.Paso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasoMapper {

    PasoDTO toDTO(Paso paso);

    Paso toEntity(PasoDTO pasoDTO);
}
