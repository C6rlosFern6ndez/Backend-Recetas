package com.library.recetas.controller;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.service.RecetaService;
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
 */
@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    // Servicio que maneja la lógica de negocio para las recetas.
    private final RecetaService recetaService;

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
}
