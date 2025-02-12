package com.AdrianAlejandroEstradaCine.cine.controllers;

import com.AdrianAlejandroEstradaCine.cine.entities.Director;
import com.AdrianAlejandroEstradaCine.cine.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<Director>> obtenerTodos() {
        return ResponseEntity.ok(directorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> obtenerPorId(@PathVariable Long id) {
        Optional<Director> director = directorService.obtenerPorId(id);
        return director.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Director> crearDirector(@RequestBody Director director) {
        return ResponseEntity.ok(directorService.crearDirector(director));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDirector(@PathVariable Long id) {
        directorService.eliminarDirector(id);
        return ResponseEntity.noContent().build();
    }
}
