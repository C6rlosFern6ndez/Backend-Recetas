package com.library.recetas.controller;

import com.library.recetas.service.IngredienteService;
import com.library.recetas.dto.IngredienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> getAllIngredientes() {
        List<IngredienteDTO> ingredientes = ingredienteService.findAll();
        return new ResponseEntity<>(ingredientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> getIngredienteById(@PathVariable Integer id) {
        IngredienteDTO ingredienteDTO = ingredienteService.findById(id);
        return new ResponseEntity<>(ingredienteDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IngredienteDTO> createIngrediente(@RequestBody IngredienteDTO ingredienteDTO) {
        IngredienteDTO savedIngredienteDTO = ingredienteService.save(ingredienteDTO);
        return new ResponseEntity<>(savedIngredienteDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> updateIngrediente(@PathVariable Integer id, @RequestBody IngredienteDTO ingredienteDTO) {
        IngredienteDTO updatedIngredienteDTO = ingredienteService.update(id, ingredienteDTO);
        return new ResponseEntity<>(updatedIngredienteDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Integer id) {
        ingredienteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
