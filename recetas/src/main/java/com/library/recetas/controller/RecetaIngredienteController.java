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

@RestController
@RequestMapping("/api/receta-ingredientes")
public class RecetaIngredienteController {

    @Autowired
    private RecetaIngredienteService recetaIngredienteService;

    @GetMapping
    public ResponseEntity<List<RecetaIngrediente>> getAllRecetaIngredientes() {
        List<RecetaIngrediente> recetaIngredientes = recetaIngredienteService.findAll();
        return new ResponseEntity<>(recetaIngredientes, HttpStatus.OK);
    }

    @GetMapping("/{recetaId}/{ingredienteId}")
    public ResponseEntity<RecetaIngrediente> getRecetaIngredienteById(@PathVariable Integer recetaId, @PathVariable Integer ingredienteId) {
        RecetaIngredienteId id = new RecetaIngredienteId(recetaId, ingredienteId);
        Optional<RecetaIngrediente> recetaIngrediente = recetaIngredienteService.findById(id);
        return recetaIngrediente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RecetaIngrediente> createRecetaIngrediente(@RequestBody RecetaIngrediente recetaIngrediente) {
        RecetaIngrediente savedRecetaIngrediente = recetaIngredienteService.save(recetaIngrediente);
        return new ResponseEntity<>(savedRecetaIngrediente, HttpStatus.CREATED);
    }

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
