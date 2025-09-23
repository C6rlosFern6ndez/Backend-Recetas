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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T13:12:41+0200",
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

        receta.setId( recetaDTO.getId() );
        receta.setTitulo( recetaDTO.getTitulo() );
        receta.setDescripcion( recetaDTO.getDescripcion() );
        receta.setTiempoPreparacion( recetaDTO.getTiempoPreparacion() );
        receta.setDificultad( recetaDTO.getDificultad() );
        receta.setPorciones( recetaDTO.getPorciones() );
        receta.setUsuario( usuarioMapper.toEntity( recetaDTO.getUsuario() ) );
        receta.setFechaCreacion( recetaDTO.getFechaCreacion() );
        receta.setPasos( pasoDTOSetToPasoSet( recetaDTO.getPasos() ) );
        receta.setComentarios( comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() ) );
        receta.setCalificaciones( calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() ) );
        receta.setRecetaIngredientes( recetaIngredienteDTOSetToRecetaIngredienteSet( recetaDTO.getRecetaIngredientes() ) );
        receta.setCategorias( categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() ) );

        return receta;
    }

    @Override
    public Receta updateEntityFromDTO(RecetaDTO recetaDTO, Receta receta) {
        if ( recetaDTO == null ) {
            return receta;
        }

        receta.setTitulo( recetaDTO.getTitulo() );
        receta.setDescripcion( recetaDTO.getDescripcion() );
        receta.setTiempoPreparacion( recetaDTO.getTiempoPreparacion() );
        receta.setDificultad( recetaDTO.getDificultad() );
        receta.setPorciones( recetaDTO.getPorciones() );
        if ( receta.getPasos() != null ) {
            Set<Paso> set = pasoDTOSetToPasoSet( recetaDTO.getPasos() );
            if ( set != null ) {
                receta.getPasos().clear();
                receta.getPasos().addAll( set );
            }
            else {
                receta.setPasos( null );
            }
        }
        else {
            Set<Paso> set = pasoDTOSetToPasoSet( recetaDTO.getPasos() );
            if ( set != null ) {
                receta.setPasos( set );
            }
        }
        if ( receta.getComentarios() != null ) {
            Set<Comentario> set1 = comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() );
            if ( set1 != null ) {
                receta.getComentarios().clear();
                receta.getComentarios().addAll( set1 );
            }
            else {
                receta.setComentarios( null );
            }
        }
        else {
            Set<Comentario> set1 = comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() );
            if ( set1 != null ) {
                receta.setComentarios( set1 );
            }
        }
        if ( receta.getCalificaciones() != null ) {
            Set<Calificacion> set2 = calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() );
            if ( set2 != null ) {
                receta.getCalificaciones().clear();
                receta.getCalificaciones().addAll( set2 );
            }
            else {
                receta.setCalificaciones( null );
            }
        }
        else {
            Set<Calificacion> set2 = calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() );
            if ( set2 != null ) {
                receta.setCalificaciones( set2 );
            }
        }
        if ( receta.getRecetaIngredientes() != null ) {
            Set<RecetaIngrediente> set3 = recetaIngredienteDTOSetToRecetaIngredienteSet( recetaDTO.getRecetaIngredientes() );
            if ( set3 != null ) {
                receta.getRecetaIngredientes().clear();
                receta.getRecetaIngredientes().addAll( set3 );
            }
            else {
                receta.setRecetaIngredientes( null );
            }
        }
        else {
            Set<RecetaIngrediente> set3 = recetaIngredienteDTOSetToRecetaIngredienteSet( recetaDTO.getRecetaIngredientes() );
            if ( set3 != null ) {
                receta.setRecetaIngredientes( set3 );
            }
        }
        if ( receta.getCategorias() != null ) {
            Set<Categoria> set4 = categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() );
            if ( set4 != null ) {
                receta.getCategorias().clear();
                receta.getCategorias().addAll( set4 );
            }
            else {
                receta.setCategorias( null );
            }
        }
        else {
            Set<Categoria> set4 = categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() );
            if ( set4 != null ) {
                receta.setCategorias( set4 );
            }
        }

        return receta;
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

    protected Comentario comentarioDTOToComentario(ComentarioDTO comentarioDTO) {
        if ( comentarioDTO == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setId( comentarioDTO.getId() );
        comentario.setUsuario( usuarioMapper.toEntity( comentarioDTO.getUsuario() ) );
        comentario.setComentario( comentarioDTO.getComentario() );
        comentario.setFechaComentario( comentarioDTO.getFechaComentario() );

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

    protected Calificacion calificacionDTOToCalificacion(CalificacionDTO calificacionDTO) {
        if ( calificacionDTO == null ) {
            return null;
        }

        Calificacion calificacion = new Calificacion();

        calificacion.setId( calificacionDTO.getId() );
        calificacion.setUsuario( usuarioMapper.toEntity( calificacionDTO.getUsuario() ) );
        calificacion.setPuntuacion( calificacionDTO.getPuntuacion() );
        calificacion.setFechaCalificacion( calificacionDTO.getFechaCalificacion() );

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
}
