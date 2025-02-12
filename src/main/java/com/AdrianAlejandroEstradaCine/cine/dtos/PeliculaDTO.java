package com.AdrianAlejandroEstradaCine.cine.dtos;

import com.AdrianAlejandroEstradaCine.cine.entities.Actor;
import com.AdrianAlejandroEstradaCine.cine.entities.Pelicula;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class PeliculaDTO {
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String titulo;
    @Size(max = 255)
    private String descripcion;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotNull
    private Long directorId;

    @NotNull
    private Long generoId;

    private Set<Long> actoresIds;

    private String directorNombre;
    private String generoNombre;

    public PeliculaDTO(Pelicula pelicula) {
        this.id = pelicula.getId();
        this.titulo = pelicula.getTitulo();
        this.descripcion = pelicula.getDescripcion();
        this.directorNombre = (pelicula.getDirector() != null) ? pelicula.getDirector().getNombre() : null;
        this.generoNombre = (pelicula.getGenero() != null) ? pelicula.getGenero().getNombre() : null;
        this.actoresNombres = (pelicula.getActores() != null)
                ? pelicula.getActores().stream().map(Actor::getNombre).collect(Collectors.toSet())
                : null;
    }

    public String getDirectorNombre() {
        return directorNombre;
    }

    public void setDirectorNombre(String directorNombre) {
        this.directorNombre = directorNombre;
    }

    public String getGeneroNombre() {
        return generoNombre;
    }

    public void setGeneroNombre(String generoNombre) {
        this.generoNombre = generoNombre;
    }

    public Set<String> getActoresNombres() {
        return actoresNombres;
    }

    public void setActoresNombres(Set<String> actoresNombres) {
        this.actoresNombres = actoresNombres;
    }

    private Set<String> actoresNombres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public Set<Long> getActoresIds() {
        return actoresIds;
    }

    public void setActoresIds(Set<Long> actoresIds) {
        this.actoresIds = actoresIds;
    }




}
