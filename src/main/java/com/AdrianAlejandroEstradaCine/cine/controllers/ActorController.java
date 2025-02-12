package com.AdrianAlejandroEstradaCine.cine.controllers;

import com.AdrianAlejandroEstradaCine.cine.entities.Actor;
import com.AdrianAlejandroEstradaCine.cine.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/actores")
@RequiredArgsConstructor
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public ResponseEntity<List<Actor>> obtenerTodos() {
        return ResponseEntity.ok(actorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> obtenerPorId(@PathVariable Long id) {
        Optional<Actor> actor = actorService.obtenerPorId(id);
        return actor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Actor> crearActor(@RequestBody Actor actor) {
        return ResponseEntity.ok(actorService.crearActor(actor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarActor(@PathVariable Long id) {
        actorService.eliminarActor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top")
    public ResponseEntity<List<Actor>> obtenerTop5Actores() {
        return ResponseEntity.ok(actorService.obtenerTop5Actores());
    }
}
