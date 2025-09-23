package com.library.recetas.mapper;

import com.library.recetas.dto.RecetaIngredienteDTO;
import com.library.recetas.model.Ingrediente;
import com.library.recetas.model.Receta;
import com.library.recetas.model.RecetaIngrediente;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T08:15:19+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RecetaIngredienteMapperImpl implements RecetaIngredienteMapper {

    @Autowired
    private IngredienteMapper ingredienteMapper;

    @Override
    public RecetaIngredienteDTO toDTO(RecetaIngrediente recetaIngrediente) {
        if ( recetaIngrediente == null ) {
            return null;
        }

        RecetaIngredienteDTO recetaIngredienteDTO = new RecetaIngredienteDTO();

        recetaIngredienteDTO.setRecetaId( recetaIngredienteRecetaId( recetaIngrediente ) );
        recetaIngredienteDTO.setIngredienteId( recetaIngredienteIngredienteId( recetaIngrediente ) );
        recetaIngredienteDTO.setIngrediente( ingredienteMapper.toDTO( recetaIngrediente.getIngrediente() ) );
        recetaIngredienteDTO.setCantidad( recetaIngrediente.getCantidad() );

        return recetaIngredienteDTO;
    }

    @Override
    public RecetaIngrediente toEntity(RecetaIngredienteDTO recetaIngredienteDTO) {
        if ( recetaIngredienteDTO == null ) {
            return null;
        }

        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();

        recetaIngrediente.setReceta( recetaIngredienteDTOToReceta( recetaIngredienteDTO ) );
        recetaIngrediente.setIngrediente( recetaIngredienteDTOToIngrediente( recetaIngredienteDTO ) );
        recetaIngrediente.setId( recetaIngredienteDTOToRecetaIngredienteId( recetaIngredienteDTO ) );
        recetaIngrediente.setCantidad( recetaIngredienteDTO.getCantidad() );

        return recetaIngrediente;
    }

    private Integer recetaIngredienteRecetaId(RecetaIngrediente recetaIngrediente) {
        if ( recetaIngrediente == null ) {
            return null;
        }
        Receta receta = recetaIngrediente.getReceta();
        if ( receta == null ) {
            return null;
        }
        Integer id = receta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer recetaIngredienteIngredienteId(RecetaIngrediente recetaIngrediente) {
        if ( recetaIngrediente == null ) {
            return null;
        }
        Ingrediente ingrediente = recetaIngrediente.getIngrediente();
        if ( ingrediente == null ) {
            return null;
        }
        Integer id = ingrediente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Receta recetaIngredienteDTOToReceta(RecetaIngredienteDTO recetaIngredienteDTO) {
        if ( recetaIngredienteDTO == null ) {
            return null;
        }

        Receta receta = new Receta();

        receta.setId( recetaIngredienteDTO.getRecetaId() );

        return receta;
    }

    protected Ingrediente recetaIngredienteDTOToIngrediente(RecetaIngredienteDTO recetaIngredienteDTO) {
        if ( recetaIngredienteDTO == null ) {
            return null;
        }

        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setId( recetaIngredienteDTO.getIngredienteId() );

        return ingrediente;
    }

    protected RecetaIngrediente.RecetaIngredienteId recetaIngredienteDTOToRecetaIngredienteId(RecetaIngredienteDTO recetaIngredienteDTO) {
        if ( recetaIngredienteDTO == null ) {
            return null;
        }

        RecetaIngrediente.RecetaIngredienteId recetaIngredienteId = new RecetaIngrediente.RecetaIngredienteId();

        recetaIngredienteId.setRecetaId( recetaIngredienteDTO.getRecetaId() );
        recetaIngredienteId.setIngredienteId( recetaIngredienteDTO.getIngredienteId() );

        return recetaIngredienteId;
    }
}
