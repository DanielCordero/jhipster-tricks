package ml.danielcordero.tests.aplicacion.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConfiguracionVariable entity.
 */
public class ConfiguracionVariableDTO implements Serializable {

    private Long id;

    private LocalDate fecha;

    private Integer veces;

    private Integer otrocampo;

    private String campoadicional;

    private Long variableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getVeces() {
        return veces;
    }

    public void setVeces(Integer veces) {
        this.veces = veces;
    }

    public Integer getOtrocampo() {
        return otrocampo;
    }

    public void setOtrocampo(Integer otrocampo) {
        this.otrocampo = otrocampo;
    }

    public String getCampoadicional() {
        return campoadicional;
    }

    public void setCampoadicional(String campoadicional) {
        this.campoadicional = campoadicional;
    }

    public Long getVariableId() {
        return variableId;
    }

    public void setVariableId(Long variableId) {
        this.variableId = variableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfiguracionVariableDTO configuracionVariableDTO = (ConfiguracionVariableDTO) o;
        if (configuracionVariableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configuracionVariableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfiguracionVariableDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", veces=" + getVeces() +
            ", otrocampo=" + getOtrocampo() +
            ", campoadicional='" + getCampoadicional() + "'" +
            ", variable=" + getVariableId() +
            "}";
    }
}
