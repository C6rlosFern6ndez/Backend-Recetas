package com.library.recetas.mapper;

import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.model.Comentario;
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
        comentarioDTO.setId( comentario.getId() );
        comentarioDTO.setComentario( comentario.getComentario() );
        comentarioDTO.setFechaComentario( comentario.getFechaComentario() );
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
