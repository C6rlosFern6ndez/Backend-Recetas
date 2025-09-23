package com.library.recetas.controller;

import com.library.recetas.dto.RecetaDTO;
import com.library.recetas.mapper.RecetaMapper;
import com.library.recetas.model.Receta;
import com.library.recetas.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;
    @Autowired
    private RecetaMapper recetaMapper;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RecetaDTO>> getAllRecetas() {
        List<Receta> recetas = recetaService.findAll();
        List<RecetaDTO> recetaDTOs = recetaMapper.toDTOs(recetas);
        return new ResponseEntity<>(recetaDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RecetaDTO> getRecetaById(@PathVariable Integer id) {
        Optional<Receta> receta = recetaService.findById(id);
        return receta.map(value -> new ResponseEntity<>(recetaMapper.toDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RecetaDTO> createReceta(@RequestBody RecetaDTO recetaDTO) {
        Receta receta = recetaMapper.toEntity(recetaDTO);
        Receta savedReceta = recetaService.save(receta);
        return new ResponseEntity<>(recetaMapper.toDTO(savedReceta), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @recetaService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<RecetaDTO> updateReceta(@PathVariable Integer id, @RequestBody RecetaDTO recetaDTO) {
        Optional<Receta> existingReceta = recetaService.findById(id);
        if (existingReceta.isPresent()) {
            Receta recetaToUpdate = existingReceta.get();
            recetaMapper.updateEntityFromDTO(recetaDTO, recetaToUpdate);
            Receta updatedReceta = recetaService.save(recetaToUpdate);
            return new ResponseEntity<>(recetaMapper.toDTO(updatedReceta), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and @recetaService.isOwner(#id, authentication.principal.id)")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        if (recetaService.findById(id).isPresent()) {
            recetaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
