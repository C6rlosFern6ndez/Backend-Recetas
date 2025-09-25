package com.library.recetas.controller;

import com.library.recetas.service.UsuarioService;
import com.library.recetas.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los usuarios.
 * Proporciona endpoints para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    // Servicio que maneja la lógica de negocio para los usuarios.
    private final UsuarioService usuarioService;

    /**
     * Obtiene todos los usuarios registrados.
     * @return ResponseEntity con una lista de todos los usuarios (DTOs) y estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    /**
     * Obtiene un usuario específico por su ID.
     * @param id El ID del usuario a buscar.
     * @return ResponseEntity con el usuario encontrado (DTO) y estado HTTP OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    /**
     * Crea un nuevo usuario.
     * @param usuarioDTO El objeto UsuarioDTO a guardar.
     * @return ResponseEntity con el usuario creado (DTO) y estado HTTP CREATED.
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioDTO));
    }

    /**
     * Actualiza un usuario existente por su ID.
     * @param id El ID del usuario a actualizar.
     * @param usuarioDTO El objeto UsuarioDTO con los datos actualizados.
     * @return ResponseEntity con el usuario actualizado (DTO) y estado HTTP OK.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioDTO));
    }

    /**
     * Elimina un usuario por su ID.
     * @param id El ID del usuario a eliminar.
     * @return ResponseEntity con estado HTTP NO_CONTENT si la eliminación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
