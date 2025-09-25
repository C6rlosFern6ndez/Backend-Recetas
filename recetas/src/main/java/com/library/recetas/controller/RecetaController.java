package com.library.recetas.controller;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.service.RecetaService;
import com.library.recetas.service.FavoritoService; // Importamos el servicio de favoritos
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las recetas.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las recetas.
 * Incluye autorizaciones de seguridad para proteger los endpoints.
 * También incluye un endpoint para la búsqueda avanzada de recetas y gestión de favoritos.
 */
@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    // Servicio que maneja la lógica de negocio para las recetas.
    private final RecetaService recetaService;
    // Servicio para la gestión de favoritos.
    private final FavoritoService favoritoService;

    /**
     * Obtiene todas las recetas registradas.
     * Requiere que el usuario esté autenticado.
     * @return ResponseEntity con una lista de todas las recetas (DTOs) y estado HTTP OK.
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecetaDTO>> getAllRecetas() {
        return ResponseEntity.ok(recetaService.findAll());
    }

    /**
     * Obtiene una receta específica por su ID.
     * Requiere que el usuario esté autenticado.
     * @param id El ID de la receta a buscar.
     * @return ResponseEntity con la receta encontrada (DTO) y estado HTTP OK.
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RecetaDTO> getRecetaById(@PathVariable Integer id) {
        return ResponseEntity.ok(recetaService.findById(id));
    }

    /**
     * Crea una nueva receta.
     * Requiere que el usuario esté autenticado.
     * @param recetaDTO El objeto RecetaDTO a guardar.
     * @return ResponseEntity con la receta creada (DTO) y estado HTTP CREATED.
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RecetaDTO> createReceta(@RequestBody RecetaDTO recetaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaService.save(recetaDTO));
    }

    /**
     * Actualiza una receta existente por su ID.
     * Requiere que el usuario esté autenticado y sea el propietario de la receta.
     * @param id El ID de la receta a actualizar.
     * @param recetaDTO El objeto RecetaDTO con los datos actualizados.
     * @return ResponseEntity con la receta actualizada (DTO) y estado HTTP OK.
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @recetaService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<RecetaDTO> updateReceta(@PathVariable Integer id, @RequestBody RecetaDTO recetaDTO) {
        return ResponseEntity.ok(recetaService.update(id, recetaDTO));
    }

    /**
     * Elimina una receta por su ID.
     * Requiere que el usuario esté autenticado y sea el propietario de la receta.
     * @param id El ID de la receta a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @recetaService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        recetaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca recetas basándose en múltiples criterios opcionales.
     * Los criterios se pasan como parámetros de consulta (query parameters).
     *
     * @param categoriaNombre Nombre de la categoría (opcional).
     * @param maxTiempoPreparacion Tiempo máximo de preparación (opcional).
     * @param searchTerm Término de búsqueda para título o descripción (opcional).
     * @param ingredienteNombre Nombre del ingrediente (opcional).
     * @param dificultad Nombre de la dificultad (opcional).
     * @param minPuntuacion Puntuación mínima de calificación (opcional).
     * @return ResponseEntity con una lista de RecetaDTO que coinciden con los criterios.
     */
    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecetaDTO>> searchRecetas(
            @RequestParam(required = false) String categoriaNombre,
            @RequestParam(required = false) Integer maxTiempoPreparacion,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(required = false) String ingredienteNombre,
            @RequestParam(required = false) String dificultad,
            @RequestParam(required = false) Integer minPuntuacion) {
        
        List<RecetaDTO> resultados = recetaService.searchRecetas(categoriaNombre, maxTiempoPreparacion, searchTerm, ingredienteNombre, dificultad, minPuntuacion);
        return ResponseEntity.ok(resultados);
    }

    // --- Endpoints para Favoritos ---

    /**
     * Añade una receta a los favoritos del usuario autenticado.
     * @param recetaId El ID de la receta a añadir a favoritos.
     * @return ResponseEntity con estado HTTP CREATED si se añadió correctamente.
     */
    @PostMapping("/favoritos/{recetaId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> addFavorito(@PathVariable Integer recetaId) {
        favoritoService.addFavorito(recetaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Elimina una receta de los favoritos del usuario autenticado.
     * @param recetaId El ID de la receta a eliminar de favoritos.
     * @return ResponseEntity con estado HTTP NO_CONTENT si se eliminó correctamente.
     */
    @DeleteMapping("/favoritos/{recetaId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> removeFavorito(@PathVariable Integer recetaId) {
        favoritoService.removeFavorito(recetaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene la lista de recetas favoritas del usuario autenticado.
     * @param usuarioId El ID del usuario (debe coincidir con el usuario autenticado).
     * @return ResponseEntity con una lista de RecetaDTOs favoritas.
     */
    @GetMapping("/favoritos/{usuarioId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecetaDTO>> getFavoritos(@PathVariable Integer usuarioId) {
        // La validación de que el usuarioId coincide con el usuario autenticado se hace dentro del servicio.
        return ResponseEntity.ok(favoritoService.getFavoritos(usuarioId));
    }
}
