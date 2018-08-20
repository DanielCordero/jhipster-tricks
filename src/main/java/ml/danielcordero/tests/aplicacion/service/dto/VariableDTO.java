package ml.danielcordero.tests.aplicacion.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Variable entity.
 */
public class VariableDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    private String campo;

    @NotNull
    private Integer cuak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getCuak() {
        return cuak;
    }

    public void setCuak(Integer cuak) {
        this.cuak = cuak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VariableDTO variableDTO = (VariableDTO) o;
        if (variableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VariableDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", campo='" + getCampo() + "'" +
            ", cuak=" + getCuak() +
            "}";
    }
}
