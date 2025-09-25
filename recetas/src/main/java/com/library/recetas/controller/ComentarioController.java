package com.library.recetas.controller;

import com.library.recetas.model.Comentario;
import com.library.recetas.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar los comentarios de las recetas.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los comentarios.
 */
@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    // Servicio que maneja la lógica de negocio para los comentarios.
    @Autowired
    private ComentarioService comentarioService;

    /**
     * Obtiene todos los comentarios registrados.
     * @return ResponseEntity con una lista de todos los comentarios y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<Comentario>> getAllComentarios() {
        List<Comentario> comentarios = comentarioService.findAll();
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    /**
     * Obtiene un comentario específico por su ID.
     * @param id El ID del comentario a buscar.
     * @return ResponseEntity con el comentario encontrado y estado HTTP OK, o NOT_FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Optional<Comentario> comentario = comentarioService.findById(id);
        return comentario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Crea un nuevo comentario.
     * @param comentario El objeto Comentario a guardar.
     * @return ResponseEntity con el comentario creado y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioService.save(comentario);
        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED);
    }

    /**
     * Actualiza un comentario existente por su ID.
     * @param id El ID del comentario a actualizar.
     * @param comentario El objeto Comentario con los datos actualizados.
     * @return ResponseEntity con el comentario actualizado y estado HTTP OK, o NOT_FOUND si el comentario no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Integer id, @RequestBody Comentario comentario) {
        if (comentarioService.findById(id).isPresent()) {
            comentario.setId(id); // Asegura que se actualiza la entidad correcta
            Comentario updatedComentario = comentarioService.save(comentario);
            return new ResponseEntity<>(updatedComentario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un comentario por su ID.
     * @param id El ID del comentario a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa, o NOT_FOUND si el comentario no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        if (comentarioService.findById(id).isPresent()) {
            comentarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
