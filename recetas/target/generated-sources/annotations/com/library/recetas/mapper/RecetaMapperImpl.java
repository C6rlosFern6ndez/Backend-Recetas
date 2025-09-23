package com.library.recetas.mapper;

import com.library.recetas.dto.CalificacionDTO;
import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.dto.ComentarioDTO;
import com.library.recetas.dto.PasoDTO;
import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.dto.RecetaIngredienteDTO;
import com.library.recetas.model.Calificacion;
import com.library.recetas.model.Categoria;
import com.library.recetas.model.Comentario;
import com.library.recetas.model.Paso;
import com.library.recetas.model.Receta;
import com.library.recetas.model.RecetaIngrediente;
import com.library.recetas.model.Usuario;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T12:17:08+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class RecetaMapperImpl implements RecetaMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private PasoMapper pasoMapper;
    @Autowired
    private RecetaIngredienteMapper recetaIngredienteMapper;

    @Override
    public RecetaDTO toDTO(Receta receta) {
        if ( receta == null ) {
            return null;
        }

        RecetaDTO recetaDTO = new RecetaDTO();

        recetaDTO.setCalificaciones( calificacionSetToCalificacionDTOSet( receta.getCalificaciones() ) );
        recetaDTO.setCategorias( categoriaSetToCategoriaDTOSet( receta.getCategorias() ) );
        recetaDTO.setComentarios( comentarioSetToComentarioDTOSet( receta.getComentarios() ) );
        recetaDTO.setDescripcion( receta.getDescripcion() );
        recetaDTO.setDificultad( receta.getDificultad() );
        recetaDTO.setFechaCreacion( receta.getFechaCreacion() );
        recetaDTO.setId( receta.getId() );
        recetaDTO.setPasos( pasoSetToPasoDTOSet( receta.getPasos() ) );
        recetaDTO.setPorciones( receta.getPorciones() );
        recetaDTO.setRecetaIngredientes( recetaIngredienteSetToRecetaIngredienteDTOSet( receta.getRecetaIngredientes() ) );
        recetaDTO.setTiempoPreparacion( receta.getTiempoPreparacion() );
        recetaDTO.setTitulo( receta.getTitulo() );
        recetaDTO.setUsuario( usuarioMapper.toDTO( receta.getUsuario() ) );

        return recetaDTO;
    }

    @Override
    public List<RecetaDTO> toDTOs(List<Receta> recetas) {
        if ( recetas == null ) {
            return null;
        }

        List<RecetaDTO> list = new ArrayList<RecetaDTO>( recetas.size() );
        for ( Receta receta : recetas ) {
            list.add( toDTO( receta ) );
        }

        return list;
    }

    @Override
    public Receta toEntity(RecetaDTO recetaDTO) {
        if ( recetaDTO == null ) {
            return null;
        }

        Receta receta = new Receta();

        receta.setCalificaciones( calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() ) );
        receta.setCategorias( categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() ) );
        receta.setComentarios( comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() ) );
        receta.setDescripcion( recetaDTO.getDescripcion() );
        receta.setDificultad( recetaDTO.getDificultad() );
        receta.setFechaCreacion( recetaDTO.getFechaCreacion() );
        receta.setId( recetaDTO.getId() );
        receta.setPasos( pasoDTOSetToPasoSet( recetaDTO.getPasos() ) );
        receta.setPorciones( recetaDTO.getPorciones() );
        receta.setRecetaIngredientes( recetaIngredienteDTOSetToRecetaIngredienteSet( recetaDTO.getRecetaIngredientes() ) );
        receta.setTiempoPreparacion( recetaDTO.getTiempoPreparacion() );
        receta.setTitulo( recetaDTO.getTitulo() );
        receta.setUsuario( usuarioMapper.toEntity( recetaDTO.getUsuario() ) );

        return receta;
    }

    @Override
    public void updateEntityFromDTO(RecetaDTO recetaDTO, Receta receta) {
        if ( recetaDTO == null ) {
            return;
        }

        if ( receta.getCalificaciones() != null ) {
            Set<Calificacion> set = calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() );
            if ( set != null ) {
                receta.getCalificaciones().clear();
                receta.getCalificaciones().addAll( set );
            }
            else {
                receta.setCalificaciones( null );
            }
        }
        else {
            Set<Calificacion> set = calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() );
            if ( set != null ) {
                receta.setCalificaciones( set );
            }
        }
        if ( receta.getCategorias() != null ) {
            Set<Categoria> set1 = categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() );
            if ( set1 != null ) {
                receta.getCategorias().clear();
                receta.getCategorias().addAll( set1 );
            }
            else {
                receta.setCategorias( null );
            }
        }
        else {
            Set<Categoria> set1 = categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() );
            if ( set1 != null ) {
                receta.setCategorias( set1 );
            }
        }
        if ( receta.getComentarios() != null ) {
            Set<Comentario> set2 = comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() );
            if ( set2 != null ) {
                receta.getComentarios().clear();
                receta.getComentarios().addAll( set2 );
            }
            else {
                receta.setComentarios( null );
            }
        }
        else {
            Set<Comentario> set2 = comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() );
            if ( set2 != null ) {
                receta.setComentarios( set2 );
            }
        }
        receta.setDescripcion( recetaDTO.getDescripcion() );
        receta.setDificultad( recetaDTO.getDificultad() );
        receta.setFechaCreacion( recetaDTO.getFechaCreacion() );
        receta.setId( recetaDTO.getId() );
        if ( receta.getPasos() != null ) {
            Set<Paso> set3 = pasoDTOSetToPasoSet( recetaDTO.getPasos() );
            if ( set3 != null ) {
                receta.getPasos().clear();
                receta.getPasos().addAll( set3 );
            }
            else {
                receta.setPasos( null );
            }
        }
        else {
            Set<Paso> set3 = pasoDTOSetToPasoSet( recetaDTO.getPasos() );
            if ( set3 != null ) {
                receta.setPasos( set3 );
            }
        }
        receta.setPorciones( recetaDTO.getPorciones() );
        receta.setTiempoPreparacion( recetaDTO.getTiempoPreparacion() );
        receta.setTitulo( recetaDTO.getTitulo() );
        if ( recetaDTO.getUsuario() != null ) {
            if ( receta.getUsuario() == null ) {
                receta.setUsuario( new Usuario() );
            }
            usuarioMapper.updateEntityFromDto( recetaDTO.getUsuario(), receta.getUsuario() );
        }
        else {
            receta.setUsuario( null );
        }
    }

    protected CalificacionDTO calificacionToCalificacionDTO(Calificacion calificacion) {
        if ( calificacion == null ) {
            return null;
        }

        CalificacionDTO calificacionDTO = new CalificacionDTO();

        calificacionDTO.setFechaCalificacion( calificacion.getFechaCalificacion() );
        calificacionDTO.setId( calificacion.getId() );
        calificacionDTO.setPuntuacion( calificacion.getPuntuacion() );
        calificacionDTO.setUsuario( usuarioMapper.toDTO( calificacion.getUsuario() ) );

        return calificacionDTO;
    }

    protected Set<CalificacionDTO> calificacionSetToCalificacionDTOSet(Set<Calificacion> set) {
        if ( set == null ) {
            return null;
        }

        Set<CalificacionDTO> set1 = new LinkedHashSet<CalificacionDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Calificacion calificacion : set ) {
            set1.add( calificacionToCalificacionDTO( calificacion ) );
        }

        return set1;
    }

    protected Set<CategoriaDTO> categoriaSetToCategoriaDTOSet(Set<Categoria> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoriaDTO> set1 = new LinkedHashSet<CategoriaDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Categoria categoria : set ) {
            set1.add( categoriaMapper.toDTO( categoria ) );
        }

        return set1;
    }

    protected ComentarioDTO comentarioToComentarioDTO(Comentario comentario) {
        if ( comentario == null ) {
            return null;
        }

        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setComentario( comentario.getComentario() );
        comentarioDTO.setFechaComentario( comentario.getFechaComentario() );
        comentarioDTO.setId( comentario.getId() );
        comentarioDTO.setUsuario( usuarioMapper.toDTO( comentario.getUsuario() ) );

        return comentarioDTO;
    }

    protected Set<ComentarioDTO> comentarioSetToComentarioDTOSet(Set<Comentario> set) {
        if ( set == null ) {
            return null;
        }

        Set<ComentarioDTO> set1 = new LinkedHashSet<ComentarioDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Comentario comentario : set ) {
            set1.add( comentarioToComentarioDTO( comentario ) );
        }

        return set1;
    }

    protected Set<PasoDTO> pasoSetToPasoDTOSet(Set<Paso> set) {
        if ( set == null ) {
            return null;
        }

        Set<PasoDTO> set1 = new LinkedHashSet<PasoDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Paso paso : set ) {
            set1.add( pasoMapper.toDTO( paso ) );
        }

        return set1;
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

    protected Calificacion calificacionDTOToCalificacion(CalificacionDTO calificacionDTO) {
        if ( calificacionDTO == null ) {
            return null;
        }

        Calificacion calificacion = new Calificacion();

        calificacion.setFechaCalificacion( calificacionDTO.getFechaCalificacion() );
        calificacion.setId( calificacionDTO.getId() );
        calificacion.setPuntuacion( calificacionDTO.getPuntuacion() );
        calificacion.setUsuario( usuarioMapper.toEntity( calificacionDTO.getUsuario() ) );

        return calificacion;
    }

    protected Set<Calificacion> calificacionDTOSetToCalificacionSet(Set<CalificacionDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Calificacion> set1 = new LinkedHashSet<Calificacion>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CalificacionDTO calificacionDTO : set ) {
            set1.add( calificacionDTOToCalificacion( calificacionDTO ) );
        }

        return set1;
    }

    protected Set<Categoria> categoriaDTOSetToCategoriaSet(Set<CategoriaDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Categoria> set1 = new LinkedHashSet<Categoria>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoriaDTO categoriaDTO : set ) {
            set1.add( categoriaMapper.toEntity( categoriaDTO ) );
        }

        return set1;
    }

    protected Comentario comentarioDTOToComentario(ComentarioDTO comentarioDTO) {
        if ( comentarioDTO == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setComentario( comentarioDTO.getComentario() );
        comentario.setFechaComentario( comentarioDTO.getFechaComentario() );
        comentario.setId( comentarioDTO.getId() );
        comentario.setUsuario( usuarioMapper.toEntity( comentarioDTO.getUsuario() ) );

        return comentario;
    }

    protected Set<Comentario> comentarioDTOSetToComentarioSet(Set<ComentarioDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Comentario> set1 = new LinkedHashSet<Comentario>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ComentarioDTO comentarioDTO : set ) {
            set1.add( comentarioDTOToComentario( comentarioDTO ) );
        }

        return set1;
    }

    protected Set<Paso> pasoDTOSetToPasoSet(Set<PasoDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Paso> set1 = new LinkedHashSet<Paso>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PasoDTO pasoDTO : set ) {
            set1.add( pasoMapper.toEntity( pasoDTO ) );
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
