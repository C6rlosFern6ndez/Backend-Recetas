package com.library.recetas.mapper;

import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.model.Comentario;
import com.library.recetas.model.Receta;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T19:01:28+0200",
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

        comentarioDTO.setRecetaId( comentarioRecetaId( comentario ) );
        comentarioDTO.setComentario( comentario.getComentario() );
        comentarioDTO.setFechaComentario( comentario.getFechaComentario() );
        comentarioDTO.setId( comentario.getId() );
        comentarioDTO.setUsuario( usuarioMapper.toDTO( comentario.getUsuario() ) );

        return comentarioDTO;
    }

    @Override
    public Comentario toEntity(ComentarioDTO comentarioDTO) {
        if ( comentarioDTO == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setId( comentarioDTO.getId() );
        comentario.setUsuario( usuarioMapper.toEntity( comentarioDTO.getUsuario() ) );
        comentario.setComentario( comentarioDTO.getComentario() );
        comentario.setFechaComentario( comentarioDTO.getFechaComentario() );

        comentario.setReceta( comentarioDTO.getRecetaId() != null ? new com.library.recetas.model.Receta(comentarioDTO.getRecetaId()) : null );

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
