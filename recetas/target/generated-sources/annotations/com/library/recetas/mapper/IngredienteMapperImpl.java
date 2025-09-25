package com.library.recetas.mapper;

import com.library.recetas.dto.IngredienteDTO;
import com.library.recetas.dto.RecetaIngredienteDTO;
import com.library.recetas.model.Ingrediente;
import com.library.recetas.model.RecetaIngrediente;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T11:44:15+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class IngredienteMapperImpl implements IngredienteMapper {

    @Autowired
    private RecetaIngredienteMapper recetaIngredienteMapper;

    @Override
    public IngredienteDTO toDTO(Ingrediente ingrediente) {
        if ( ingrediente == null ) {
            return null;
        }

        IngredienteDTO ingredienteDTO = new IngredienteDTO();

        ingredienteDTO.setId( ingrediente.getId() );
        ingredienteDTO.setNombre( ingrediente.getNombre() );
        ingredienteDTO.setRecetaIngredientes( recetaIngredienteSetToRecetaIngredienteDTOSet( ingrediente.getRecetaIngredientes() ) );

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
        ingrediente.setRecetaIngredientes( recetaIngredienteDTOSetToRecetaIngredienteSet( ingredienteDTO.getRecetaIngredientes() ) );

        return ingrediente;
    }

    protected Set<RecetaIngredienteDTO> recetaIngredienteSetToRecetaIngredienteDTOSet(Set<RecetaIngrediente> set) {
        if ( set == null ) {
            return null;
        }

        Set<RecetaIngredienteDTO> set1 = new LinkedHashSet<RecetaIngredienteDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RecetaIngrediente recetaIngrediente : set ) {
            set1.add( recetaIngredienteMapper.toDTO( recetaIngrediente ) );
        }

        return set1;
    }

    protected Set<RecetaIngrediente> recetaIngredienteDTOSetToRecetaIngredienteSet(Set<RecetaIngredienteDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RecetaIngrediente> set1 = new LinkedHashSet<RecetaIngrediente>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RecetaIngredienteDTO recetaIngredienteDTO : set ) {
            set1.add( recetaIngredienteMapper.toEntity( recetaIngredienteDTO ) );
        }

        return set1;
    }
}
