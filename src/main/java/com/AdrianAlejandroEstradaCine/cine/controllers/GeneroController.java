package com.AdrianAlejandroEstradaCine.cine.controllers;

import com.AdrianAlejandroEstradaCine.cine.entities.Genero;
import com.AdrianAlejandroEstradaCine.cine.services.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    @Autowired
    private  GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<Genero>> obtenerTodos() {
        return ResponseEntity.ok(generoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerPorId(@PathVariable Long id) {
        Optional<Genero> genero = generoService.obtenerPorId(id);
        return genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Genero> crearGenero(@RequestBody Genero genero) {
        return ResponseEntity.ok(generoService.crearGenero(genero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable Long id) {
        generoService.eliminarGenero(id);
        return ResponseEntity.noContent().build();
    }
}
