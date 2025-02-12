package com.AdrianAlejandroEstradaCine.cine.services;

import com.AdrianAlejandroEstradaCine.cine.entities.Director;
import com.AdrianAlejandroEstradaCine.cine.repositories.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorService {

    @Autowired
    private  DirectorRepository directorRepository;

    public List<Director> obtenerTodos() {
        return directorRepository.findAll();
    }

    public Optional<Director> obtenerPorId(Long id) {
        return directorRepository.findById(id);
    }

    public Director crearDirector(Director director) {
        return directorRepository.save(director);
    }

    public void eliminarDirector(Long id) {
        directorRepository.deleteById(id);
    }
}
