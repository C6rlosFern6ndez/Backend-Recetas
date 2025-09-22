package com.library.recetas.controller;

import com.library.recetas.model.Receta;
import com.library.recetas.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<Receta>> getAllRecetas() {
        List<Receta> recetas = recetaService.findAll();
        return new ResponseEntity<>(recetas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Integer id) {
        Optional<Receta> receta = recetaService.findById(id);
        return receta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Receta> createReceta(@RequestBody Receta receta) {
        Receta savedReceta = recetaService.save(receta);
        return new ResponseEntity<>(savedReceta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta(@PathVariable Integer id, @RequestBody Receta receta) {
        if (recetaService.findById(id).isPresent()) {
            receta.setId(id);
            Receta updatedReceta = recetaService.save(receta);
            return new ResponseEntity<>(updatedReceta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        if (recetaService.findById(id).isPresent()) {
            recetaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
