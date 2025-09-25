package com.library.recetas.mapper;

import com.library.recetas.dto.CategoriaDTO;
import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.model.Categoria;
import com.library.recetas.model.Receta;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T11:44:15+0200",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Autowired
    private RecetaMapper recetaMapper;

    @Override
    public CategoriaDTO toDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId( categoria.getId() );
        categoriaDTO.setNombre( categoria.getNombre() );
        categoriaDTO.setRecetas( recetaSetToRecetaDTOSet( categoria.getRecetas() ) );

        return categoriaDTO;
    }

    @Override
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( categoriaDTO.getId() );
        categoria.setNombre( categoriaDTO.getNombre() );
        categoria.setRecetas( recetaDTOSetToRecetaSet( categoriaDTO.getRecetas() ) );

        return categoria;
    }

    protected Set<RecetaDTO> recetaSetToRecetaDTOSet(Set<Receta> set) {
        if ( set == null ) {
            return null;
        }

        Set<RecetaDTO> set1 = new LinkedHashSet<RecetaDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Receta receta : set ) {
            set1.add( recetaMapper.toDTO( receta ) );
        }

        return set1;
    }

    protected Set<Receta> recetaDTOSetToRecetaSet(Set<RecetaDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Receta> set1 = new LinkedHashSet<Receta>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RecetaDTO recetaDTO : set ) {
            set1.add( recetaMapper.toEntity( recetaDTO ) );
        }

        return set1;
    }
}
