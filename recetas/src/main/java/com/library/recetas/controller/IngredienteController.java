package com.library.recetas.controller;

import com.library.recetas.service.IngredienteService;
import com.library.recetas.dto.IngredienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los ingredientes de las recetas.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los ingredientes.
 */
@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    // Servicio que maneja la lógica de negocio para los ingredientes.
    @Autowired
    private IngredienteService ingredienteService;

    /**
     * Obtiene todos los ingredientes registrados.
     * @return ResponseEntity con una lista de todos los ingredientes (DTOs) y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> getAllIngredientes() {
        List<IngredienteDTO> ingredientes = ingredienteService.findAll();
        return new ResponseEntity<>(ingredientes, HttpStatus.OK);
    }

    /**
     * Obtiene un ingrediente específico por su ID.
     * @param id El ID del ingrediente a buscar.
     * @return ResponseEntity con el ingrediente encontrado (DTO) y estado HTTP OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> getIngredienteById(@PathVariable Integer id) {
        IngredienteDTO ingredienteDTO = ingredienteService.findById(id);
        // Asumiendo que findById lanza una excepción si no se encuentra, o devuelve null/Optional.
        // Si devuelve null, se podría añadir una comprobación:
        // if (ingredienteDTO == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ingredienteDTO, HttpStatus.OK);
    }

    /**
     * Crea un nuevo ingrediente.
     * @param ingredienteDTO El objeto IngredienteDTO a guardar.
     * @return ResponseEntity con el ingrediente creado (DTO) y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<IngredienteDTO> createIngrediente(@RequestBody IngredienteDTO ingredienteDTO) {
        IngredienteDTO savedIngredienteDTO = ingredienteService.save(ingredienteDTO);
        return new ResponseEntity<>(savedIngredienteDTO, HttpStatus.CREATED);
    }

    /**
     * Actualiza un ingrediente existente por su ID.
     * @param id El ID del ingrediente a actualizar.
     * @param ingredienteDTO El objeto IngredienteDTO con los datos actualizados.
     * @return ResponseEntity con el ingrediente actualizado (DTO) y estado HTTP OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> updateIngrediente(@PathVariable Integer id, @RequestBody IngredienteDTO ingredienteDTO) {
        IngredienteDTO updatedIngredienteDTO = ingredienteService.update(id, ingredienteDTO);
        return new ResponseEntity<>(updatedIngredienteDTO, HttpStatus.OK);
    }

    /**
     * Elimina un ingrediente por su ID.
     * @param id El ID del ingrediente a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Integer id) {
        ingredienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
