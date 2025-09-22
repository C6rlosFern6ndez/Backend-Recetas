package com.library.recetas.mapper;

import com.library.recetas.dto.PasoDTO;
import com.library.recetas.model.Paso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PasoMapper {

    PasoMapper INSTANCE = Mappers.getMapper(PasoMapper.class);

    PasoDTO toDTO(Paso paso);

    Paso toEntity(PasoDTO pasoDTO);
}
