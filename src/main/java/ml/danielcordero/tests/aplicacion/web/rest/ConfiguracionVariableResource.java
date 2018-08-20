package ml.danielcordero.tests.aplicacion.web.rest;

import com.codahale.metrics.annotation.Timed;
import ml.danielcordero.tests.aplicacion.service.ConfiguracionVariableService;
import ml.danielcordero.tests.aplicacion.web.rest.errors.BadRequestAlertException;
import ml.danielcordero.tests.aplicacion.web.rest.util.HeaderUtil;
import ml.danielcordero.tests.aplicacion.service.dto.ConfiguracionVariableDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ConfiguracionVariable.
 */
@RestController
@RequestMapping("/api")
public class ConfiguracionVariableResource {

    private final Logger log = LoggerFactory.getLogger(ConfiguracionVariableResource.class);

    private static final String ENTITY_NAME = "configuracionVariable";

    private final ConfiguracionVariableService configuracionVariableService;

    public ConfiguracionVariableResource(ConfiguracionVariableService configuracionVariableService) {
        this.configuracionVariableService = configuracionVariableService;
    }

    /**
     * POST  /configuracion-variables : Create a new configuracionVariable.
     *
     * @param configuracionVariableDTO the configuracionVariableDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configuracionVariableDTO, or with status 400 (Bad Request) if the configuracionVariable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/configuracion-variables")
    @Timed
    public ResponseEntity<ConfiguracionVariableDTO> createConfiguracionVariable(@RequestBody ConfiguracionVariableDTO configuracionVariableDTO) throws URISyntaxException {
        log.debug("REST request to save ConfiguracionVariable : {}", configuracionVariableDTO);
        if (configuracionVariableDTO.getId() != null) {
            throw new BadRequestAlertException("A new configuracionVariable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfiguracionVariableDTO result = configuracionVariableService.save(configuracionVariableDTO);
        return ResponseEntity.created(new URI("/api/configuracion-variables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configuracion-variables : Updates an existing configuracionVariable.
     *
     * @param configuracionVariableDTO the configuracionVariableDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configuracionVariableDTO,
     * or with status 400 (Bad Request) if the configuracionVariableDTO is not valid,
     * or with status 500 (Internal Server Error) if the configuracionVariableDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/configuracion-variables")
    @Timed
    public ResponseEntity<ConfiguracionVariableDTO> updateConfiguracionVariable(@RequestBody ConfiguracionVariableDTO configuracionVariableDTO) throws URISyntaxException {
        log.debug("REST request to update ConfiguracionVariable : {}", configuracionVariableDTO);
        if (configuracionVariableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConfiguracionVariableDTO result = configuracionVariableService.save(configuracionVariableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configuracionVariableDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configuracion-variables : get all the configuracionVariables.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of configuracionVariables in body
     */
    @GetMapping("/configuracion-variables")
    @Timed
    public List<ConfiguracionVariableDTO> getAllConfiguracionVariables() {
        log.debug("REST request to get all ConfiguracionVariables");
        return configuracionVariableService.findAll();
    }

    /**
     * GET  /configuracion-variables/:id : get the "id" configuracionVariable.
     *
     * @param id the id of the configuracionVariableDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configuracionVariableDTO, or with status 404 (Not Found)
     */
    @GetMapping("/configuracion-variables/{id}")
    @Timed
    public ResponseEntity<ConfiguracionVariableDTO> getConfiguracionVariable(@PathVariable Long id) {
        log.debug("REST request to get ConfiguracionVariable : {}", id);
        Optional<ConfiguracionVariableDTO> configuracionVariableDTO = configuracionVariableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(configuracionVariableDTO);
    }

    /**
     * DELETE  /configuracion-variables/:id : delete the "id" configuracionVariable.
     *
     * @param id the id of the configuracionVariableDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/configuracion-variables/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfiguracionVariable(@PathVariable Long id) {
        log.debug("REST request to delete ConfiguracionVariable : {}", id);
        configuracionVariableService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
