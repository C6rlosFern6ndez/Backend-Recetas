package com.library.recetas.service;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.mapper.RecetaMapper;
import com.library.recetas.model.Receta;
import com.library.recetas.repository.RecetaRepository;
import com.library.recetas.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;

    public List<RecetaDTO> findAll() {
        return recetaRepository.findAll().stream()
                .map(recetaMapper::toDTO)
                .toList();
    }

    public RecetaDTO findById(Integer id) {
        return recetaRepository.findRecetaWithDetailsById(id)
                .map(recetaMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", "id", id));
    }

    public RecetaDTO save(RecetaDTO recetaDTO) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Receta receta = recetaMapper.toEntity(recetaDTO);
        receta.setUsuario(usuario);
        return recetaMapper.toDTO(recetaRepository.save(receta));
    }

    public RecetaDTO update(Integer id, RecetaDTO recetaDTO) {
        Receta existingReceta = recetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", "id", id));
        
        recetaMapper.updateEntityFromDTO(recetaDTO, existingReceta);
        return recetaMapper.toDTO(recetaRepository.save(existingReceta));
    }

    public void deleteById(Integer id) {
        if (!recetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receta", "id", id);
        }
        recetaRepository.deleteById(id);
    }

    public boolean isOwner(Integer recetaId, Integer userId) {
        return recetaRepository.findById(recetaId)
                .map(receta -> receta.getUsuario().getId().equals(userId))
                .orElse(false);
    }

    /**
     * Busca recetas basándose en múltiples criterios opcionales.
     * Los criterios se combinan mediante intersección lógica (AND).
     * Si no se proporcionan criterios, se devuelven todas las recetas.
     *
     * @param categoriaNombre Nombre de la categoría (opcional).
     * @param maxTiempoPreparacion Tiempo máximo de preparación (opcional).
     * @param searchTerm Término de búsqueda para título o descripción (opcional).
     * @param ingredienteNombre Nombre del ingrediente (opcional).
     * @param dificultad Nombre de la dificultad (opcional).
     * @param minPuntuacion Puntuación mínima de calificación (opcional).
     * @return Una lista de RecetaDTO que coinciden con todos los criterios proporcionados.
     */
    public List<RecetaDTO> searchRecetas(String categoriaNombre, Integer maxTiempoPreparacion, String searchTerm, String ingredienteNombre, String dificultad, Integer minPuntuacion) {
        Set<Receta> finalResults = null;

        // Criterio 1: Categoría y Tiempo de Preparación
        if (categoriaNombre != null && maxTiempoPreparacion != null) {
            List<Receta> categoryTimeResults = recetaRepository.findByCategoriaNombreAndTiempoPreparacionLessThanEqual(categoriaNombre, maxTiempoPreparacion);
            if (finalResults == null) {
                finalResults = new HashSet<>(categoryTimeResults);
            } else {
                finalResults.retainAll(new HashSet<>(categoryTimeResults)); // Intersección
            }
        }

        // Criterio 2: Término de Búsqueda (Título/Descripción)
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            List<Receta> termResults = recetaRepository.findByTituloOrDescripcionContaining(searchTerm);
            if (finalResults == null) {
                finalResults = new HashSet<>(termResults);
            } else {
                finalResults.retainAll(new HashSet<>(termResults)); // Intersección
            }
        }

        // Criterio 3: Ingrediente
        if (ingredienteNombre != null && !ingredienteNombre.trim().isEmpty()) {
            List<Receta> ingredientResults = recetaRepository.findByIngredienteNombre(ingredienteNombre);
            if (finalResults == null) {
                finalResults = new HashSet<>(ingredientResults);
            } else {
                finalResults.retainAll(new HashSet<>(ingredientResults)); // Intersección
            }
        }

        // Criterio 4: Dificultad
        if (dificultad != null && !dificultad.trim().isEmpty()) {
            List<Receta> dificultadResults = recetaRepository.findByDificultad(dificultad);
            if (finalResults == null) {
                finalResults = new HashSet<>(dificultadResults);
            } else {
                finalResults.retainAll(new HashSet<>(dificultadResults)); // Intersección
            }
        }

        // Criterio 5: Calificación Mínima
        if (minPuntuacion != null) {
            List<Receta> puntuacionResults = recetaRepository.findByCalificacionesMinima(minPuntuacion);
            if (finalResults == null) {
                finalResults = new HashSet<>(puntuacionResults);
            } else {
                finalResults.retainAll(new HashSet<>(puntuacionResults)); // Intersección
            }
        }

        // Si no se proporcionó ningún criterio de búsqueda, devolver todas las recetas.
        if (finalResults == null) {
            // Considerar si se debe devolver todas las recetas o una lista vacía si no hay criterios.
            // Devolver todas las recetas si no hay filtros aplicados.
            return findAll();
        }

        // Mapear los resultados finales a DTOs
        return finalResults.stream()
                .map(recetaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
