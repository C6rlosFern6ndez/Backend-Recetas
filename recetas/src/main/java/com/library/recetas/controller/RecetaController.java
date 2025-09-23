package com.library.recetas.controller;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecetaDTO>> getAllRecetas() {
        return ResponseEntity.ok(recetaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RecetaDTO> getRecetaById(@PathVariable Integer id) {
        return ResponseEntity.ok(recetaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RecetaDTO> createReceta(@RequestBody RecetaDTO recetaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recetaService.save(recetaDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @recetaService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<RecetaDTO> updateReceta(@PathVariable Integer id, @RequestBody RecetaDTO recetaDTO) {
        return ResponseEntity.ok(recetaService.update(id, recetaDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @recetaService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        recetaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
