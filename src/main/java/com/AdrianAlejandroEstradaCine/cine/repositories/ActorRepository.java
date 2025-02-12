package com.AdrianAlejandroEstradaCine.cine.repositories;

import com.AdrianAlejandroEstradaCine.cine.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findTop5ByOrderByPeliculasDesc();
}
