package com.AdrianAlejandroEstradaCine.cine.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter

public class CreatePeliculaDTO {
    @NotBlank
    @Size(max = 100)
    private String titulo;

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

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
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

    @Size(max = 255)
    private String descripcion;

    @NotNull
    private Long directorId;

    @NotNull
    private Long generoId;

    private Set<Long> actoresIds;
}

