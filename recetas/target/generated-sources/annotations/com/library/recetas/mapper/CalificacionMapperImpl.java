package com.library.recetas.mapper;

import com.library.recetas.dto.CalificacionDTO;
import com.library.recetas.model.Calificacion;
import com.library.recetas.model.Receta;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T11:53:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CalificacionMapperImpl implements CalificacionMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public CalificacionDTO toDTO(Calificacion calificacion) {
        if ( calificacion == null ) {
            return null;
        }

        CalificacionDTO calificacionDTO = new CalificacionDTO();

        calificacionDTO.setRecetaId( calificacionRecetaId( calificacion ) );
        calificacionDTO.setId( calificacion.getId() );
        calificacionDTO.setPuntuacion( calificacion.getPuntuacion() );
        calificacionDTO.setFechaCalificacion( calificacion.getFechaCalificacion() );
        calificacionDTO.setUsuario( usuarioMapper.toDTO( calificacion.getUsuario() ) );

        return calificacionDTO;
    }

    @Override
    public Calificacion toEntity(CalificacionDTO calificacionDTO) {
        if ( calificacionDTO == null ) {
            return null;
        }

        Calificacion calificacion = new Calificacion();

        calificacion.setId( calificacionDTO.getId() );
        calificacion.setUsuario( usuarioMapper.toEntity( calificacionDTO.getUsuario() ) );
        calificacion.setPuntuacion( calificacionDTO.getPuntuacion() );
        calificacion.setFechaCalificacion( calificacionDTO.getFechaCalificacion() );

        calificacion.setReceta( calificacionDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(calificacionDTO.getRecetaId()) : null );

        return calificacion;
    }

    private Integer calificacionRecetaId(Calificacion calificacion) {
        if ( calificacion == null ) {
            return null;
        }
        Receta receta = calificacion.getReceta();
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
