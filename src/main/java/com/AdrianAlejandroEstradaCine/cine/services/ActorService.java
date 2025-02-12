package com.AdrianAlejandroEstradaCine.cine.services;

import com.AdrianAlejandroEstradaCine.cine.entities.Actor;
import com.AdrianAlejandroEstradaCine.cine.repositories.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> obtenerTodos() {
        return actorRepository.findAll();
    }

    public Optional<Actor> obtenerPorId(Long id) {
        return actorRepository.findById(id);
    }

    public Actor crearActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void eliminarActor(Long id) {
        actorRepository.deleteById(id);
    }

    public List<Actor> obtenerTop5Actores() {
        return actorRepository.findTop5ByOrderByPeliculasDesc();
    }
}
