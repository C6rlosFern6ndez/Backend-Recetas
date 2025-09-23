package com.library.recetas.mapper;

import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.model.Ingrediente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T10:58:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
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
