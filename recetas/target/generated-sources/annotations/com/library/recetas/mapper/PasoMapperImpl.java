package com.library.recetas.mapper;

import com.library.recetas.dto.PasoDTO;
import com.library.recetas.model.Paso;
import com.library.recetas.model.Receta;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T10:58:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PasoMapperImpl implements PasoMapper {

    @Override
    public PasoDTO toDTO(Paso paso) {
        if ( paso == null ) {
            return null;
        }

        PasoDTO pasoDTO = new PasoDTO();

        pasoDTO.setRecetaId( pasoRecetaId( paso ) );
        pasoDTO.setId( paso.getId() );
        pasoDTO.setOrden( paso.getOrden() );
        pasoDTO.setDescripcion( paso.getDescripcion() );

        return pasoDTO;
    }

    @Override
    public Paso toEntity(PasoDTO pasoDTO) {
        if ( pasoDTO == null ) {
            return null;
        }

        Paso paso = new Paso();

        paso.setId( pasoDTO.getId() );
        paso.setOrden( pasoDTO.getOrden() );
        paso.setDescripcion( pasoDTO.getDescripcion() );

        paso.setReceta( pasoDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(pasoDTO.getRecetaId()) : null );

        return paso;
    }

    private Integer pasoRecetaId(Paso paso) {
        if ( paso == null ) {
            return null;
        }
        Receta receta = paso.getReceta();
        if ( receta == null ) {
            return null;
        }
        Integer id = receta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
