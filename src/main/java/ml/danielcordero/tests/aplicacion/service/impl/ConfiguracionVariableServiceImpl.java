package ml.danielcordero.tests.aplicacion.service.impl;

import ml.danielcordero.tests.aplicacion.service.ConfiguracionVariableService;
import ml.danielcordero.tests.aplicacion.domain.ConfiguracionVariable;
import ml.danielcordero.tests.aplicacion.repository.ConfiguracionVariableRepository;
import ml.danielcordero.tests.aplicacion.service.dto.ConfiguracionVariableDTO;
import ml.danielcordero.tests.aplicacion.service.mapper.ConfiguracionVariableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ConfiguracionVariable.
 */
@Service
@Transactional
public class ConfiguracionVariableServiceImpl implements ConfiguracionVariableService {

    private final Logger log = LoggerFactory.getLogger(ConfiguracionVariableServiceImpl.class);

    private final ConfiguracionVariableRepository configuracionVariableRepository;

    private final ConfiguracionVariableMapper configuracionVariableMapper;

    public ConfiguracionVariableServiceImpl(ConfiguracionVariableRepository configuracionVariableRepository, ConfiguracionVariableMapper configuracionVariableMapper) {
        this.configuracionVariableRepository = configuracionVariableRepository;
        this.configuracionVariableMapper = configuracionVariableMapper;
    }

    /**
     * Save a configuracionVariable.
     *
     * @param configuracionVariableDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConfiguracionVariableDTO save(ConfiguracionVariableDTO configuracionVariableDTO) {
        log.debug("Request to save ConfiguracionVariable : {}", configuracionVariableDTO);
        ConfiguracionVariable configuracionVariable = configuracionVariableMapper.toEntity(configuracionVariableDTO);
        configuracionVariable = configuracionVariableRepository.save(configuracionVariable);
        return configuracionVariableMapper.toDto(configuracionVariable);
    }

    /**
     * Get all the configuracionVariables.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ConfiguracionVariableDTO> findAll() {
        log.debug("Request to get all ConfiguracionVariables");
        return configuracionVariableRepository.findAll().stream()
            .map(configuracionVariableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one configuracionVariable by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConfiguracionVariableDTO> findOne(Long id) {
        log.debug("Request to get ConfiguracionVariable : {}", id);
        return configuracionVariableRepository.findById(id)
            .map(configuracionVariableMapper::toDto);
    }

    /**
     * Delete the configuracionVariable by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConfiguracionVariable : {}", id);
        configuracionVariableRepository.deleteById(id);
    }
}
