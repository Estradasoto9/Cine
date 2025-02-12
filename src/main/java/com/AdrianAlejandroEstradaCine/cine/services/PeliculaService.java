package com.AdrianAlejandroEstradaCine.cine.services;

import com.AdrianAlejandroEstradaCine.cine.dtos.CreatePeliculaDTO;
import com.AdrianAlejandroEstradaCine.cine.dtos.PeliculaDTO;
import com.AdrianAlejandroEstradaCine.cine.dtos.UpdatePeliculaDTO;
import com.AdrianAlejandroEstradaCine.cine.entities.*;
import com.AdrianAlejandroEstradaCine.cine.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PeliculaService {

    @Autowired
    private  PeliculaRepository peliculaRepository;

    @Autowired
    private  DirectorRepository directorRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private  ActorRepository actorRepository;

    @Autowired
    private  PeliculaActorRepository peliculaActorRepository;

    public List<PeliculaDTO> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        return peliculas.stream().map(PeliculaDTO::new).collect(Collectors.toList());
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula crearPelicula(@RequestBody CreatePeliculaDTO peliculaDTO) {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setDescripcion(peliculaDTO.getDescripcion());

        Director director = directorRepository.findById(peliculaDTO.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Director no encontrado"));
        Genero genero = generoRepository.findById(peliculaDTO.getGeneroId())
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));

        pelicula.setDirector(director);
        pelicula.setGenero(genero);

        List<Actor> actores = actorRepository.findAllById(peliculaDTO.getActoresIds());
        pelicula.setActores(new HashSet<>(actores));

        return peliculaRepository.save(pelicula);
    }

    public Pelicula actualizarPelicula(Long id, UpdatePeliculaDTO dto) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        if (dto.getTitulo() != null) pelicula.setTitulo(dto.getTitulo());
        if (dto.getDescripcion() != null) pelicula.setDescripcion(dto.getDescripcion());

        if (dto.getDirectorId() != null) {
            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Director no encontrado"));
            pelicula.setDirector(director);
        }
        if (dto.getGeneroId() != null) {
            Genero genero = generoRepository.findById(dto.getGeneroId())
                    .orElseThrow(() -> new RuntimeException("Género no encontrado"));
            pelicula.setGenero(genero);
        }
        return peliculaRepository.save(pelicula);
    }

    public void eliminarPelicula(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new RuntimeException("Película no encontrada");
        }
        peliculaRepository.deleteById(id);
    }

    public List<Pelicula> obtenerPorDirector(Long directorId) {
        return peliculaRepository.findByDirectorId(directorId);
    }

    public List<Pelicula> obtenerPorGenero(Long generoId) {
        return peliculaRepository.findByGeneroId(generoId);
    }

    @Transactional
    public void asignarActorAPelicula(Long peliculaId, Long actorId) {
        Pelicula pelicula = peliculaRepository.findById(peliculaId)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("Actor no encontrado"));

        PeliculaActor peliculaActor = new PeliculaActor(pelicula, actor);
        peliculaActorRepository.save(peliculaActor);
    }

    @Transactional
    public void eliminarActorDePelicula(Long peliculaId, Long actorId) {
        PeliculaActor peliculaActor = peliculaActorRepository.findByPeliculaIdAndActorId(peliculaId, actorId)
                .orElseThrow(() -> new RuntimeException("Actor no está asociado a esta película"));

        peliculaActorRepository.delete(peliculaActor);
    }

    public List<Actor> obtenerTop5Actores() {
        return actorRepository.findTop5ByOrderByPeliculasDesc();
    }

}
