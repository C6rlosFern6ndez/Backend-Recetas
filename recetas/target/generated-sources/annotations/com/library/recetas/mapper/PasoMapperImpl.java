package com.library.recetas.mapper;

import com.library.recetas.dto.PasoDTO;
import com.library.recetas.model.Paso;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T08:15:19+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class PasoMapperImpl implements PasoMapper {

    @Override
    public PasoDTO toDTO(Paso paso) {
        if ( paso == null ) {
            return null;
        }

        PasoDTO pasoDTO = new PasoDTO();

        pasoDTO.setDescripcion( paso.getDescripcion() );
        pasoDTO.setId( paso.getId() );
        pasoDTO.setOrden( paso.getOrden() );

        return pasoDTO;
    }

    @Override
    public Paso toEntity(PasoDTO pasoDTO) {
        if ( pasoDTO == null ) {
            return null;
        }

        Paso paso = new Paso();

        paso.setDescripcion( pasoDTO.getDescripcion() );
        paso.setId( pasoDTO.getId() );
        paso.setOrden( pasoDTO.getOrden() );

        return paso;
    }
}
