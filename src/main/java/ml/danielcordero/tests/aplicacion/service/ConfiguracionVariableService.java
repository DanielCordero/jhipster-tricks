package ml.danielcordero.tests.aplicacion.service;

import ml.danielcordero.tests.aplicacion.service.dto.ConfiguracionVariableDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ConfiguracionVariable.
 */
public interface ConfiguracionVariableService {

    /**
     * Save a configuracionVariable.
     *
     * @param configuracionVariableDTO the entity to save
     * @return the persisted entity
     */
    ConfiguracionVariableDTO save(ConfiguracionVariableDTO configuracionVariableDTO);

    /**
     * Get all the configuracionVariables.
     *
     * @return the list of entities
     */
    List<ConfiguracionVariableDTO> findAll();


    /**
     * Get the "id" configuracionVariable.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConfiguracionVariableDTO> findOne(Long id);

    /**
     * Delete the "id" configuracionVariable.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
