package ml.danielcordero.tests.aplicacion.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ConfiguracionVariable.
 */
@Entity
@Table(name = "configuracion_variable")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConfiguracionVariable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "veces")
    private Integer veces;

    @Column(name = "otrocampo")
    private Integer otrocampo;

    @Column(name = "campoadicional")
    private String campoadicional;

    @ManyToOne
    @JsonIgnoreProperties("configuracionVariables")
    private Variable variable;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ConfiguracionVariable fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getVeces() {
        return veces;
    }

    public ConfiguracionVariable veces(Integer veces) {
        this.veces = veces;
        return this;
    }

    public void setVeces(Integer veces) {
        this.veces = veces;
    }

    public Integer getOtrocampo() {
        return otrocampo;
    }

    public ConfiguracionVariable otrocampo(Integer otrocampo) {
        this.otrocampo = otrocampo;
        return this;
    }

    public void setOtrocampo(Integer otrocampo) {
        this.otrocampo = otrocampo;
    }

    public String getCampoadicional() {
        return campoadicional;
    }

    public ConfiguracionVariable campoadicional(String campoadicional) {
        this.campoadicional = campoadicional;
        return this;
    }

    public void setCampoadicional(String campoadicional) {
        this.campoadicional = campoadicional;
    }

    public Variable getVariable() {
        return variable;
    }

    public ConfiguracionVariable variable(Variable variable) {
        this.variable = variable;
        return this;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
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
        ConfiguracionVariable configuracionVariable = (ConfiguracionVariable) o;
        if (configuracionVariable.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configuracionVariable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfiguracionVariable{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", veces=" + getVeces() +
            ", otrocampo=" + getOtrocampo() +
            ", campoadicional='" + getCampoadicional() + "'" +
            "}";
    }
}
