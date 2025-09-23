package com.library.recetas.mapper;

import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.model.Comentario;
import com.library.recetas.model.Receta;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T08:20:26+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ComentarioMapperImpl implements ComentarioMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public ComentarioDTO toDTO(Comentario comentario) {
        if ( comentario == null ) {
            return null;
        }

        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setUsuario( usuarioMapper.toDTO( comentario.getUsuario() ) );
        comentarioDTO.setRecetaId( comentarioRecetaId( comentario ) );
        comentarioDTO.setComentario( comentario.getComentario() );
        comentarioDTO.setFechaComentario( comentario.getFechaComentario() );
        comentarioDTO.setId( comentario.getId() );

        return comentarioDTO;
    }

    @Override
    public Comentario toEntity(ComentarioDTO comentarioDTO) {
        if ( comentarioDTO == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setUsuario( usuarioMapper.toEntity( comentarioDTO.getUsuario() ) );
        comentario.setComentario( comentarioDTO.getComentario() );
        comentario.setFechaComentario( comentarioDTO.getFechaComentario() );
        comentario.setId( comentarioDTO.getId() );

        comentario.setReceta( new Receta(recetaId) );

        return comentario;
    }

    private Integer comentarioRecetaId(Comentario comentario) {
        if ( comentario == null ) {
            return null;
        }
        Receta receta = comentario.getReceta();
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
