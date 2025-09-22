package com.library.recetas.controller;

import com.library.recetas.model.Paso;
import com.library.recetas.service.PasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pasos")
public class PasoController {

    @Autowired
    private PasoService pasoService;

    @GetMapping
    public ResponseEntity<List<Paso>> getAllPasos() {
        List<Paso> pasos = pasoService.findAll();
        return new ResponseEntity<>(pasos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paso> getPasoById(@PathVariable Integer id) {
        Optional<Paso> paso = pasoService.findById(id);
        return paso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Paso> createPaso(@RequestBody Paso paso) {
        Paso savedPaso = pasoService.save(paso);
        return new ResponseEntity<>(savedPaso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paso> updatePaso(@PathVariable Integer id, @RequestBody Paso paso) {
        if (pasoService.findById(id).isPresent()) {
            paso.setId(id);
            Paso updatedPaso = pasoService.save(paso);
            return new ResponseEntity<>(updatedPaso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaso(@PathVariable Integer id) {
        if (pasoService.findById(id).isPresent()) {
            pasoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
