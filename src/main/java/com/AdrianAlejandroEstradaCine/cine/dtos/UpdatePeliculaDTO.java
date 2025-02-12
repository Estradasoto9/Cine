package com.AdrianAlejandroEstradaCine.cine.dtos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePeliculaDTO {
    @Size(max = 150)
    private String titulo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Long> getActoresIds() {
        return actoresIds;
    }

    public void setActoresIds(Set<Long> actoresIds) {
        this.actoresIds = actoresIds;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Size(max = 300)
    private String descripcion;

    private Long directorId;

    private Long generoId;

    private Set<Long> actoresIds;
}