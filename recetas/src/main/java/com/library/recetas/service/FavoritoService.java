package com.library.recetas.service;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.exception.ResourceNotFoundException;
import com.library.recetas.exception.OperationNotAllowedException;
import com.library.recetas.model.Favorito;
import com.library.recetas.model.Receta;
import com.library.recetas.model.Usuario;
import com.library.recetas.repository.FavoritoRepository;
import com.library.recetas.repository.RecetaRepository;
import com.library.recetas.repository.UsuarioRepository; // Necesario para obtener el Usuario si no está en el contexto
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final RecetaRepository recetaRepository;
    private final UsuarioRepository usuarioRepository; // Inyectamos UsuarioRepository

    /**
     * Añade una receta a los favoritos de un usuario.
     * Crea una copia de los detalles clave de la receta para almacenarla.
     * @param recetaId El ID de la receta a añadir a favoritos.
     */
    public void addFavorito(Integer recetaId) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        // Verificar si la receta existe
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new ResourceNotFoundException("Receta", "id", recetaId));

        // Verificar si ya está en favoritos para este usuario
        if (favoritoRepository.findByUsuarioIdAndRecetaOriginalId(usuario.getId(), recetaId).isPresent()) {
            throw new OperationNotAllowedException("La receta ya está en tus favoritos.");
        }

        // Crear el objeto Favorito con los datos clave de la receta
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setRecetaOriginalId(receta.getId());
        favorito.setRecetaTitulo(receta.getTitulo());
        favorito.setRecetaDescripcion(receta.getDescripcion());
        favorito.setRecetaTiempoPreparacion(receta.getTiempoPreparacion());
        favorito.setRecetaDificultad(receta.getDificultad());
        favorito.setFechaFavorito(LocalDateTime.now());

        favoritoRepository.save(favorito);
    }

    /**
     * Elimina una receta de los favoritos de un usuario.
     * @param recetaId El ID de la receta original a eliminar de favoritos.
     */
    public void removeFavorito(Integer recetaId) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        // Verificar si el favorito existe para este usuario y receta
        favoritoRepository.findByUsuarioIdAndRecetaOriginalId(usuario.getId(), recetaId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito", "recetaId y usuarioId", recetaId + " y " + usuario.getId()));
        
        favoritoRepository.deleteByUsuarioIdAndRecetaOriginalId(usuario.getId(), recetaId);
    }

    /**
     * Obtiene la lista de recetas favoritas de un usuario.
     * Devuelve una lista de RecetaDTOs, que son copias de los datos guardados en Favorito.
     * @return Una lista de RecetaDTOs favoritas.
     */
    public List<RecetaDTO> getFavoritos(Integer usuarioId) {
        // Opcional: Si se quiere asegurar que el usuarioId corresponde al usuario autenticado
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!usuarioAutenticado.getId().equals(usuarioId)) {
             throw new OperationNotAllowedException("No tienes permiso para ver los favoritos de otro usuario.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        List<Favorito> favoritos = favoritoRepository.findByUsuario(usuario);
        
        // Convertir la lista de Favorito a lista de RecetaDTO.
        // Nota: Esto es una conversión simplificada. Si se necesitaran todos los detalles
        // de la receta original (ingredientes, pasos, etc.), se requeriría una lógica adicional
        // para buscarlos o almacenarlos de forma más completa en la entidad Favorito.
        return favoritos.stream()
                .map(this::convertToRecetaDTO) // Usamos un método auxiliar para la conversión
                .collect(Collectors.toList());
    }

    /**
     * Método auxiliar para convertir una entidad Favorito a RecetaDTO.
     * Crea un RecetaDTO basado en los datos 'snapshot' almacenados en Favorito.
     * @param favorito El objeto Favorito a convertir.
     * @return Un RecetaDTO con los datos del favorito.
     */
    private RecetaDTO convertToRecetaDTO(Favorito favorito) {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setId(favorito.getRecetaOriginalId()); // Usamos el ID original
        recetaDTO.setTitulo(favorito.getRecetaTitulo());
        recetaDTO.setDescripcion(favorito.getRecetaDescripcion());
        recetaDTO.setTiempoPreparacion(favorito.getRecetaTiempoPreparacion());
        // Corrección: El DTO espera un String para la dificultad.
        // La entidad Favorito almacena el enum Dificultad.
        // Convertimos el String de vuelta a un enum Dificultad.
        if (favorito.getRecetaDificultad() != null) {
            // Convertimos el String (obtenido de favorito.getRecetaDificultad().name()) de vuelta a Dificultad enum
            recetaDTO.setDificultad(com.library.recetas.model.Dificultad.valueOf(favorito.getRecetaDificultad().name())); 
        }
        // Nota: Campos como ingredientes, pasos, comentarios, calificaciones no se pueden
        // reconstruir directamente desde el snapshot de Favorito. Se dejarían nulos o vacíos.
        // Si se necesita el usuario que creó la receta original, se necesitaría almacenar su ID también.
        return recetaDTO;
    }
}
