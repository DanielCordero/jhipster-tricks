package ml.danielcordero.tests.aplicacion.repository;

import ml.danielcordero.tests.aplicacion.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
