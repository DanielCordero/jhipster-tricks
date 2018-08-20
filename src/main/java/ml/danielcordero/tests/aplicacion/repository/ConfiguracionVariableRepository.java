package ml.danielcordero.tests.aplicacion.repository;

import ml.danielcordero.tests.aplicacion.domain.ConfiguracionVariable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConfiguracionVariable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfiguracionVariableRepository extends JpaRepository<ConfiguracionVariable, Long> {

}
