package com.AdrianAlejandroEstradaCine.cine.controllers;

import com.AdrianAlejandroEstradaCine.cine.dtos.CreatePeliculaDTO;
import com.AdrianAlejandroEstradaCine.cine.dtos.PeliculaDTO;
import com.AdrianAlejandroEstradaCine.cine.dtos.UpdatePeliculaDTO;
import com.AdrianAlejandroEstradaCine.cine.entities.Actor;
import com.AdrianAlejandroEstradaCine.cine.entities.Pelicula;
import com.AdrianAlejandroEstradaCine.cine.repositories.PeliculaRepository;
import com.AdrianAlejandroEstradaCine.cine.services.PeliculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    @Autowired
    private  PeliculaService peliculaService;

    @GetMapping
    public List<PeliculaDTO> getPeliculas() {
        return peliculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.obtenerPorId(id);
        return pelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody CreatePeliculaDTO peliculaDTO) {
        Pelicula nuevaPelicula = peliculaService.crearPelicula(peliculaDTO);
        return ResponseEntity.ok(nuevaPelicula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody UpdatePeliculaDTO dto) {
        Pelicula peliculaActualizada = peliculaService.actualizarPelicula(id, dto);
        return ResponseEntity.ok(peliculaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        peliculaService.eliminarPelicula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/director/{directorId}")
    public List<Pelicula> obtenerPeliculasPorDirector(@PathVariable Long directorId) {
        return peliculaService.obtenerPorDirector(directorId);
    }

    @GetMapping("/genero/{generoId}")
    public List<Pelicula> obtenerPeliculasPorGenero(@PathVariable Long generoId) {
        return peliculaService.obtenerPorGenero(generoId);
    }

    @PostMapping("/{peliculaId}/asignar-actor/{actorId}")
    public ResponseEntity<Void> asignarActorAPelicula(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        peliculaService.asignarActorAPelicula(peliculaId, actorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{peliculaId}/eliminar-actor/{actorId}")
    public ResponseEntity<Void> eliminarActorDePelicula(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        peliculaService.eliminarActorDePelicula(peliculaId, actorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/top5-actores")
    public List<Actor> obtenerTop5Actores() {
        return peliculaService.obtenerTop5Actores();
    }
}
