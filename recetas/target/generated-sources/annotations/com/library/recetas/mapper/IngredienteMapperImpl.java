package com.library.recetas.mapper;

import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.model.Ingrediente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T08:15:19+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class IngredienteMapperImpl implements IngredienteMapper {

    @Override
    public IngredienteDTO toDTO(Ingrediente ingrediente) {
        if ( ingrediente == null ) {
            return null;
        }

        IngredienteDTO ingredienteDTO = new IngredienteDTO();

        ingredienteDTO.setId( ingrediente.getId() );
        ingredienteDTO.setNombre( ingrediente.getNombre() );

        return ingredienteDTO;
    }

    @Override
    public Ingrediente toEntity(IngredienteDTO ingredienteDTO) {
        if ( ingredienteDTO == null ) {
            return null;
        }

        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setId( ingredienteDTO.getId() );
        ingrediente.setNombre( ingredienteDTO.getNombre() );

        return ingrediente;
    }
}
