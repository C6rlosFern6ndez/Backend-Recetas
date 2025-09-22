package com.library.recetas.controller;

import com.library.recetas.model.Ingrediente;
import com.library.recetas.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<Ingrediente>> getAllIngredientes() {
        List<Ingrediente> ingredientes = ingredienteService.findAll();
        return new ResponseEntity<>(ingredientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable Integer id) {
        Optional<Ingrediente> ingrediente = ingredienteService.findById(id);
        return ingrediente.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente savedIngrediente = ingredienteService.save(ingrediente);
        return new ResponseEntity<>(savedIngrediente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Integer id, @RequestBody Ingrediente ingrediente) {
        if (ingredienteService.findById(id).isPresent()) {
            ingrediente.setId(id);
            Ingrediente updatedIngrediente = ingredienteService.save(ingrediente);
            return new ResponseEntity<>(updatedIngrediente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Integer id) {
        if (ingredienteService.findById(id).isPresent()) {
            ingredienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
