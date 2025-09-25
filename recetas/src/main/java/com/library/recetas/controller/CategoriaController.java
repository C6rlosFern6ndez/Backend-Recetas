package com.library.recetas.controller;

import com.library.recetas.service.CategoriaService;
import com.library.recetas.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las categorías de recetas.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las categorías.
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    // Servicio que maneja la lógica de negocio para las categorías.
    @Autowired
    private CategoriaService categoriaService;

    /**
     * Obtiene todas las categorías registradas.
     * @return ResponseEntity con una lista de todas las categorías (DTOs) y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    /**
     * Obtiene una categoría específica por su ID.
     * @param id El ID de la categoría a buscar.
     * @return ResponseEntity con la categoría encontrada (DTO) y estado HTTP OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Integer id) {
        CategoriaDTO categoriaDTO = categoriaService.findById(id);
        // Asumiendo que findById lanza una excepción si no se encuentra, o devuelve null/Optional.
        // Si devuelve null, se podría añadir una comprobación:
        // if (categoriaDTO == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
    }

    /**
     * Crea una nueva categoría.
     * @param categoriaDTO El objeto CategoriaDTO a guardar.
     * @return ResponseEntity con la categoría creada (DTO) y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO savedCategoriaDTO = categoriaService.save(categoriaDTO);
        return new ResponseEntity<>(savedCategoriaDTO, HttpStatus.CREATED);
    }

    /**
     * Actualiza una categoría existente por su ID.
     * @param id El ID de la categoría a actualizar.
     * @param categoriaDTO El objeto CategoriaDTO con los datos actualizados.
     * @return ResponseEntity con la categoría actualizada (DTO) y estado HTTP OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Integer id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO updatedCategoriaDTO = categoriaService.update(id, categoriaDTO);
        return new ResponseEntity<>(updatedCategoriaDTO, HttpStatus.OK);
    }

    /**
     * Elimina una categoría por su ID.
     * @param id El ID de la categoría a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        categoriaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
