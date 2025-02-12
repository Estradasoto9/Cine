package com.AdrianAlejandroEstradaCine.cine.services;

import com.AdrianAlejandroEstradaCine.cine.entities.Genero;
import com.AdrianAlejandroEstradaCine.cine.repositories.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneroService {

    @Autowired
    private  GeneroRepository generoRepository;

    public List<Genero> obtenerTodos() {
        return generoRepository.findAll();
    }

    public Optional<Genero> obtenerPorId(Long id) {
        return generoRepository.findById(id);
    }

    public Genero crearGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    public void eliminarGenero(Long id) {
        generoRepository.deleteById(id);
    }
}
