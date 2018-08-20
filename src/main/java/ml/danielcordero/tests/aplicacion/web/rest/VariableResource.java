package ml.danielcordero.tests.aplicacion.web.rest;

import com.codahale.metrics.annotation.Timed;
import ml.danielcordero.tests.aplicacion.domain.Variable;
import ml.danielcordero.tests.aplicacion.repository.VariableRepository;
import ml.danielcordero.tests.aplicacion.web.rest.errors.BadRequestAlertException;
import ml.danielcordero.tests.aplicacion.web.rest.util.HeaderUtil;
import ml.danielcordero.tests.aplicacion.web.rest.util.PaginationUtil;
import ml.danielcordero.tests.aplicacion.service.dto.VariableDTO;
import ml.danielcordero.tests.aplicacion.service.mapper.VariableMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Variable.
 */
@RestController
@RequestMapping("/api")
public class VariableResource {

    private final Logger log = LoggerFactory.getLogger(VariableResource.class);

    private static final String ENTITY_NAME = "variable";

    private final VariableRepository variableRepository;

    private final VariableMapper variableMapper;

    public VariableResource(VariableRepository variableRepository, VariableMapper variableMapper) {
        this.variableRepository = variableRepository;
        this.variableMapper = variableMapper;
    }

    /**
     * POST  /variables : Create a new variable.
     *
     * @param variableDTO the variableDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new variableDTO, or with status 400 (Bad Request) if the variable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/variables")
    @Timed
    public ResponseEntity<VariableDTO> createVariable(@Valid @RequestBody VariableDTO variableDTO) throws URISyntaxException {
        log.debug("REST request to save Variable : {}", variableDTO);
        if (variableDTO.getId() != null) {
            throw new BadRequestAlertException("A new variable cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Variable variable = variableMapper.toEntity(variableDTO);
        variable = variableRepository.save(variable);
        VariableDTO result = variableMapper.toDto(variable);
        return ResponseEntity.created(new URI("/api/variables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /variables : Updates an existing variable.
     *
     * @param variableDTO the variableDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated variableDTO,
     * or with status 400 (Bad Request) if the variableDTO is not valid,
     * or with status 500 (Internal Server Error) if the variableDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/variables")
    @Timed
    public ResponseEntity<VariableDTO> updateVariable(@Valid @RequestBody VariableDTO variableDTO) throws URISyntaxException {
        log.debug("REST request to update Variable : {}", variableDTO);
        if (variableDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Variable variable = variableMapper.toEntity(variableDTO);
        variable = variableRepository.save(variable);
        VariableDTO result = variableMapper.toDto(variable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, variableDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /variables : get all the variables.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of variables in body
     */
    @GetMapping("/variables")
    @Timed
    public ResponseEntity<List<VariableDTO>> getAllVariables(Pageable pageable) {
        log.debug("REST request to get a page of Variables");
        Page<VariableDTO> page = variableRepository.findAll(pageable).map(variableMapper::toDto);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/variables");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /variables/:id : get the "id" variable.
     *
     * @param id the id of the variableDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the variableDTO, or with status 404 (Not Found)
     */
    @GetMapping("/variables/{id}")
    @Timed
    public ResponseEntity<VariableDTO> getVariable(@PathVariable Long id) {
        log.debug("REST request to get Variable : {}", id);
        Optional<VariableDTO> variableDTO = variableRepository.findById(id)
            .map(variableMapper::toDto);
        return ResponseUtil.wrapOrNotFound(variableDTO);
    }

    /**
     * DELETE  /variables/:id : delete the "id" variable.
     *
     * @param id the id of the variableDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/variables/{id}")
    @Timed
    public ResponseEntity<Void> deleteVariable(@PathVariable Long id) {
        log.debug("REST request to delete Variable : {}", id);

        variableRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
