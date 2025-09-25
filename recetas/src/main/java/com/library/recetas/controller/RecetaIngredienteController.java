package com.library.recetas.controller;

import com.library.recetas.model.RecetaIngrediente;
import com.library.recetas.model.RecetaIngrediente.RecetaIngredienteId;
import com.library.recetas.service.RecetaIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar la relación entre recetas e ingredientes.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las asociaciones receta-ingrediente.
 */
@RestController
@RequestMapping("/api/receta-ingredientes")
public class RecetaIngredienteController {

    // Servicio que maneja la lógica de negocio para la relación receta-ingrediente.
    @Autowired
    private RecetaIngredienteService recetaIngredienteService;

    /**
     * Obtiene todas las asociaciones receta-ingrediente registradas.
     * @return ResponseEntity con una lista de todas las asociaciones y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<RecetaIngrediente>> getAllRecetaIngredientes() {
        List<RecetaIngrediente> recetaIngredientes = recetaIngredienteService.findAll();
        return new ResponseEntity<>(recetaIngredientes, HttpStatus.OK);
    }

    /**
     * Obtiene una asociación receta-ingrediente específica por los IDs de receta e ingrediente.
     * @param recetaId El ID de la receta.
     * @param ingredienteId El ID del ingrediente.
     * @return ResponseEntity con la asociación encontrada y estado HTTP OK, o NOT_FOUND si no existe.
     */
    @GetMapping("/{recetaId}/{ingredienteId}")
    public ResponseEntity<RecetaIngrediente> getRecetaIngredienteById(@PathVariable Integer recetaId, @PathVariable Integer ingredienteId) {
        RecetaIngredienteId id = new RecetaIngredienteId(recetaId, ingredienteId);
        Optional<RecetaIngrediente> recetaIngrediente = recetaIngredienteService.findById(id);
        return recetaIngrediente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea una nueva asociación receta-ingrediente.
     * @param recetaIngrediente El objeto RecetaIngrediente a guardar.
     * @return ResponseEntity con la asociación creada y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<RecetaIngrediente> createRecetaIngrediente(@RequestBody RecetaIngrediente recetaIngrediente) {
        RecetaIngrediente savedRecetaIngrediente = recetaIngredienteService.save(recetaIngrediente);
        return new ResponseEntity<>(savedRecetaIngrediente, HttpStatus.CREATED);
    }

    /**
     * Actualiza una asociación receta-ingrediente existente por sus IDs.
     * @param recetaId El ID de la receta.
     * @param ingredienteId El ID del ingrediente.
     * @param recetaIngrediente El objeto RecetaIngrediente con los datos actualizados.
     * @return ResponseEntity con la asociación actualizada y estado HTTP OK, o NOT_FOUND si la asociación no existe.
     */
    @PutMapping("/{recetaId}/{ingredienteId}")
    public ResponseEntity<RecetaIngrediente> updateRecetaIngrediente(@PathVariable Integer recetaId, @PathVariable Integer ingredienteId, @RequestBody RecetaIngrediente recetaIngrediente) {
        RecetaIngredienteId id = new RecetaIngredienteId(recetaId, ingredienteId);
        if (recetaIngredienteService.findById(id).isPresent()) {
            recetaIngrediente.setId(id); // Asegurarse de que el ID esté configurado correctamente
            RecetaIngrediente updatedRecetaIngrediente = recetaIngredienteService.save(recetaIngrediente);
            return new ResponseEntity<>(updatedRecetaIngrediente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una asociación receta-ingrediente por sus IDs.
     * @param recetaId El ID de la receta.
     * @param ingredienteId El ID del ingrediente.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa, o NOT_FOUND si la asociación no existe.
     */
    @DeleteMapping("/{recetaId}/{ingredienteId}")
    public ResponseEntity<Void> deleteRecetaIngrediente(@PathVariable Integer recetaId, @PathVariable Integer ingredienteId) {
        RecetaIngredienteId id = new RecetaIngredienteId(recetaId, ingredienteId);
        if (recetaIngredienteService.findById(id).isPresent()) {
            recetaIngredienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
