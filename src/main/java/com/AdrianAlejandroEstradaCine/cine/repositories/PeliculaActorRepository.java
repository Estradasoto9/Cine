package com.AdrianAlejandroEstradaCine.cine.repositories;

import com.AdrianAlejandroEstradaCine.cine.entities.Actor;
import com.AdrianAlejandroEstradaCine.cine.entities.PeliculaActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaActorRepository extends JpaRepository<PeliculaActor, Long> {
    List<PeliculaActor> findByPeliculaId(Long peliculaId);
    List<PeliculaActor> findByActorId(Long actorId);
    Optional<PeliculaActor> findByPeliculaIdAndActorId(Long peliculaId, Long actorId);
    @Query("SELECT a FROM Actor a JOIN a.peliculas p GROUP BY a ORDER BY COUNT(p) DESC LIMIT 5")
    List<Actor> findTop5ByOrderByPeliculasDesc();

}