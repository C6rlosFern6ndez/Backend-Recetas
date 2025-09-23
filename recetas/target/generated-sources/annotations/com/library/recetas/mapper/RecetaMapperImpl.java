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
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T08:20:26+0200",
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

        recetaDTO.setUsuario( usuarioMapper.toDTO( receta.getUsuario() ) );
        recetaDTO.setCategorias( categoriaSetToCategoriaDTOSet( receta.getCategorias() ) );
        recetaDTO.setPasos( pasoSetToPasoDTOSet( receta.getPasos() ) );
        recetaDTO.setRecetaIngredientes( recetaIngredienteSetToRecetaIngredienteDTOSet( receta.getRecetaIngredientes() ) );
        recetaDTO.setComentarios( comentarioSetToComentarioDTOSet( receta.getComentarios() ) );
        recetaDTO.setCalificaciones( calificacionSetToCalificacionDTOSet( receta.getCalificaciones() ) );
        recetaDTO.setDescripcion( receta.getDescripcion() );
        recetaDTO.setDificultad( receta.getDificultad() );
        recetaDTO.setFechaCreacion( receta.getFechaCreacion() );
        recetaDTO.setId( receta.getId() );
        recetaDTO.setPorciones( receta.getPorciones() );
        recetaDTO.setTiempoPreparacion( receta.getTiempoPreparacion() );
        recetaDTO.setTitulo( receta.getTitulo() );

        return recetaDTO;
    }

    @Override
    public Receta toEntity(RecetaDTO recetaDTO) {
        if ( recetaDTO == null ) {
            return null;
        }

        Receta receta = new Receta();

        receta.setUsuario( usuarioMapper.toEntity( recetaDTO.getUsuario() ) );
        receta.setCategorias( categoriaDTOSetToCategoriaSet( recetaDTO.getCategorias() ) );
        receta.setPasos( pasoDTOSetToPasoSet( recetaDTO.getPasos() ) );
        receta.setRecetaIngredientes( recetaIngredienteDTOSetToRecetaIngredienteSet( recetaDTO.getRecetaIngredientes() ) );
        receta.setCalificaciones( calificacionDTOSetToCalificacionSet( recetaDTO.getCalificaciones() ) );
        receta.setComentarios( comentarioDTOSetToComentarioSet( recetaDTO.getComentarios() ) );
        receta.setDescripcion( recetaDTO.getDescripcion() );
        receta.setDificultad( recetaDTO.getDificultad() );
        receta.setFechaCreacion( recetaDTO.getFechaCreacion() );
        receta.setId( recetaDTO.getId() );
        receta.setPorciones( recetaDTO.getPorciones() );
        receta.setTiempoPreparacion( recetaDTO.getTiempoPreparacion() );
        receta.setTitulo( recetaDTO.getTitulo() );

        return receta;
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
}
