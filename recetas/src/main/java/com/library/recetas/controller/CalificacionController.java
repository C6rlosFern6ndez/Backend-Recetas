package com.library.recetas.controller;

import com.library.recetas.model.Calificacion;
import com.library.recetas.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar las calificaciones de las recetas.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las calificaciones.
 */
@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    // Servicio que maneja la lógica de negocio para las calificaciones.
    @Autowired
    private CalificacionService calificacionService;

    /**
     * Obtiene todas las calificaciones registradas.
     * @return ResponseEntity con una lista de todas las calificaciones y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Calificacion>> getAllCalificaciones() {
        List<Calificacion> calificaciones = calificacionService.findAll();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    /**
     * Obtiene una calificación específica por su ID.
     * @param id El ID de la calificación a buscar.
     * @return ResponseEntity con la calificación encontrada y estado HTTP OK, o NOT_FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable Integer id) {
        Optional<Calificacion> calificacion = calificacionService.findById(id);
        return calificacion.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea una nueva calificación.
     * @param calificacion El objeto Calificacion a guardar.
     * @return ResponseEntity con la calificación creada y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Calificacion> createCalificacion(@RequestBody Calificacion calificacion) {
        Calificacion savedCalificacion = calificacionService.save(calificacion);
        return new ResponseEntity<>(savedCalificacion, HttpStatus.CREATED);
    }

    /**
     * Actualiza una calificación existente por su ID.
     * @param id El ID de la calificación a actualizar.
     * @param calificacion El objeto Calificacion con los datos actualizados.
     * @return ResponseEntity con la calificación actualizada y estado HTTP OK, o NOT_FOUND si la calificación no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> updateCalificacion(@PathVariable Integer id, @RequestBody Calificacion calificacion) {
        if (calificacionService.findById(id).isPresent()) {
            calificacion.setId(id); // Asegura que se actualiza la entidad correcta
            Calificacion updatedCalificacion = calificacionService.save(calificacion);
            return new ResponseEntity<>(updatedCalificacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una calificación por su ID.
     * @param id El ID de la calificación a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa, o NOT_FOUND si la calificación no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Integer id) {
        if (calificacionService.findById(id).isPresent()) {
            calificacionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
