package com.library.recetas.controller;

import com.library.recetas.model.Paso;
import com.library.recetas.service.PasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los pasos de las recetas.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los pasos.
 */
@RestController
@RequestMapping("/api/pasos")
public class PasoController {

    // Servicio que maneja la lógica de negocio para los pasos.
    @Autowired
    private PasoService pasoService;

    /**
     * Obtiene todos los pasos registrados.
     * @return ResponseEntity con una lista de todos los pasos y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Paso>> getAllPasos() {
        List<Paso> pasos = pasoService.findAll();
        return new ResponseEntity<>(pasos, HttpStatus.OK);
    }

    /**
     * Obtiene un paso específico por su ID.
     * @param id El ID del paso a buscar.
     * @return ResponseEntity con el paso encontrado y estado HTTP OK, o NOT_FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paso> getPasoById(@PathVariable Integer id) {
        Optional<Paso> paso = pasoService.findById(id);
        return paso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea un nuevo paso.
     * @param paso El objeto Paso a guardar.
     * @return ResponseEntity con el paso creado y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Paso> createPaso(@RequestBody Paso paso) {
        Paso savedPaso = pasoService.save(paso);
        return new ResponseEntity<>(savedPaso, HttpStatus.CREATED);
    }

    /**
     * Actualiza un paso existente por su ID.
     * @param id El ID del paso a actualizar.
     * @param paso El objeto Paso con los datos actualizados.
     * @return ResponseEntity con el paso actualizado y estado HTTP OK, o NOT_FOUND si el paso no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Paso> updatePaso(@PathVariable Integer id, @RequestBody Paso paso) {
        if (pasoService.findById(id).isPresent()) {
            paso.setId(id); // Asegura que se actualiza la entidad correcta
            Paso updatedPaso = pasoService.save(paso);
            return new ResponseEntity<>(updatedPaso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un paso por su ID.
     * @param id El ID del paso a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa, o NOT_FOUND si el paso no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaso(@PathVariable Integer id) {
        if (pasoService.findById(id).isPresent()) {
            pasoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
