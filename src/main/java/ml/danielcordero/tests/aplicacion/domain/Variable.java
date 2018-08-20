package ml.danielcordero.tests.aplicacion.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Variable.
 */
@Entity
@Table(name = "variable")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Variable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "campo")
    private String campo;

    @NotNull
    @Column(name = "cuak", nullable = false)
    private Integer cuak;

    @OneToMany(mappedBy = "variable")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConfiguracionVariable> configuracionVariables = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Variable nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Variable descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCampo() {
        return campo;
    }

    public Variable campo(String campo) {
        this.campo = campo;
        return this;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getCuak() {
        return cuak;
    }

    public Variable cuak(Integer cuak) {
        this.cuak = cuak;
        return this;
    }

    public void setCuak(Integer cuak) {
        this.cuak = cuak;
    }

    public Set<ConfiguracionVariable> getConfiguracionVariables() {
        return configuracionVariables;
    }

    public Variable configuracionVariables(Set<ConfiguracionVariable> configuracionVariables) {
        this.configuracionVariables = configuracionVariables;
        return this;
    }

    public Variable addConfiguracionVariable(ConfiguracionVariable configuracionVariable) {
        this.configuracionVariables.add(configuracionVariable);
        configuracionVariable.setVariable(this);
        return this;
    }

    public Variable removeConfiguracionVariable(ConfiguracionVariable configuracionVariable) {
        this.configuracionVariables.remove(configuracionVariable);
        configuracionVariable.setVariable(null);
        return this;
    }

    public void setConfiguracionVariables(Set<ConfiguracionVariable> configuracionVariables) {
        this.configuracionVariables = configuracionVariables;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variable variable = (Variable) o;
        if (variable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Variable{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", campo='" + getCampo() + "'" +
            ", cuak=" + getCuak() +
            "}";
    }
}
