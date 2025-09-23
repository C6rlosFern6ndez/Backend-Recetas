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
    date = "2025-09-23T11:53:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
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
        recetaIngredienteDTO.setCantidad( recetaIngrediente.getCantidad() );
        recetaIngredienteDTO.setIngrediente( ingredienteMapper.toDTO( recetaIngrediente.getIngrediente() ) );

        return recetaIngredienteDTO;
    }

    @Override
    public RecetaIngrediente toEntity(RecetaIngredienteDTO recetaIngredienteDTO) {
        if ( recetaIngredienteDTO == null ) {
            return null;
        }

        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();

        recetaIngrediente.setId( recetaIngredienteDTOToRecetaIngredienteId( recetaIngredienteDTO ) );
        recetaIngrediente.setCantidad( recetaIngredienteDTO.getCantidad() );

        recetaIngrediente.setReceta( recetaIngredienteDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(recetaIngredienteDTO.getRecetaId()) : null );
        recetaIngrediente.setIngrediente( recetaIngredienteDTO.getIngredienteId() != null ? new com.library.recetas.model.Ingrediente(recetaIngredienteDTO.getIngredienteId()) : null );

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
