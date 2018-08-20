package ml.danielcordero.tests.aplicacion.web.rest;

import ml.danielcordero.tests.aplicacion.AplicacionApp;

import ml.danielcordero.tests.aplicacion.domain.Variable;
import ml.danielcordero.tests.aplicacion.repository.VariableRepository;
import ml.danielcordero.tests.aplicacion.service.dto.VariableDTO;
import ml.danielcordero.tests.aplicacion.service.mapper.VariableMapper;
import ml.danielcordero.tests.aplicacion.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static ml.danielcordero.tests.aplicacion.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VariableResource REST controller.
 *
 * @see VariableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AplicacionApp.class)
public class VariableResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPO = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUAK = 1;
    private static final Integer UPDATED_CUAK = 2;

    @Autowired
    private VariableRepository variableRepository;


    @Autowired
    private VariableMapper variableMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVariableMockMvc;

    private Variable variable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VariableResource variableResource = new VariableResource(variableRepository, variableMapper);
        this.restVariableMockMvc = MockMvcBuilders.standaloneSetup(variableResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Variable createEntity(EntityManager em) {
        Variable variable = new Variable()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .campo(DEFAULT_CAMPO)
            .cuak(DEFAULT_CUAK);
        return variable;
    }

    @Before
    public void initTest() {
        variable = createEntity(em);
    }

    @Test
    @Transactional
    public void createVariable() throws Exception {
        int databaseSizeBeforeCreate = variableRepository.findAll().size();

        // Create the Variable
        VariableDTO variableDTO = variableMapper.toDto(variable);
        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isCreated());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeCreate + 1);
        Variable testVariable = variableList.get(variableList.size() - 1);
        assertThat(testVariable.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testVariable.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testVariable.getCampo()).isEqualTo(DEFAULT_CAMPO);
        assertThat(testVariable.getCuak()).isEqualTo(DEFAULT_CUAK);
    }

    @Test
    @Transactional
    public void createVariableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = variableRepository.findAll().size();

        // Create the Variable with an existing ID
        variable.setId(1L);
        VariableDTO variableDTO = variableMapper.toDto(variable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = variableRepository.findAll().size();
        // set the field null
        variable.setNombre(null);

        // Create the Variable, which fails.
        VariableDTO variableDTO = variableMapper.toDto(variable);

        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = variableRepository.findAll().size();
        // set the field null
        variable.setDescripcion(null);

        // Create the Variable, which fails.
        VariableDTO variableDTO = variableMapper.toDto(variable);

        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCuakIsRequired() throws Exception {
        int databaseSizeBeforeTest = variableRepository.findAll().size();
        // set the field null
        variable.setCuak(null);

        // Create the Variable, which fails.
        VariableDTO variableDTO = variableMapper.toDto(variable);

        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVariables() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        // Get all the variableList
        restVariableMockMvc.perform(get("/api/variables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variable.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].campo").value(hasItem(DEFAULT_CAMPO.toString())))
            .andExpect(jsonPath("$.[*].cuak").value(hasItem(DEFAULT_CUAK)));
    }
    

    @Test
    @Transactional
    public void getVariable() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        // Get the variable
        restVariableMockMvc.perform(get("/api/variables/{id}", variable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(variable.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.campo").value(DEFAULT_CAMPO.toString()))
            .andExpect(jsonPath("$.cuak").value(DEFAULT_CUAK));
    }
    @Test
    @Transactional
    public void getNonExistingVariable() throws Exception {
        // Get the variable
        restVariableMockMvc.perform(get("/api/variables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVariable() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        int databaseSizeBeforeUpdate = variableRepository.findAll().size();

        // Update the variable
        Variable updatedVariable = variableRepository.findById(variable.getId()).get();
        // Disconnect from session so that the updates on updatedVariable are not directly saved in db
        em.detach(updatedVariable);
        updatedVariable
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .campo(UPDATED_CAMPO)
            .cuak(UPDATED_CUAK);
        VariableDTO variableDTO = variableMapper.toDto(updatedVariable);

        restVariableMockMvc.perform(put("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isOk());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeUpdate);
        Variable testVariable = variableList.get(variableList.size() - 1);
        assertThat(testVariable.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testVariable.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testVariable.getCampo()).isEqualTo(UPDATED_CAMPO);
        assertThat(testVariable.getCuak()).isEqualTo(UPDATED_CUAK);
    }

    @Test
    @Transactional
    public void updateNonExistingVariable() throws Exception {
        int databaseSizeBeforeUpdate = variableRepository.findAll().size();

        // Create the Variable
        VariableDTO variableDTO = variableMapper.toDto(variable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restVariableMockMvc.perform(put("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVariable() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        int databaseSizeBeforeDelete = variableRepository.findAll().size();

        // Get the variable
        restVariableMockMvc.perform(delete("/api/variables/{id}", variable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Variable.class);
        Variable variable1 = new Variable();
        variable1.setId(1L);
        Variable variable2 = new Variable();
        variable2.setId(variable1.getId());
        assertThat(variable1).isEqualTo(variable2);
        variable2.setId(2L);
        assertThat(variable1).isNotEqualTo(variable2);
        variable1.setId(null);
        assertThat(variable1).isNotEqualTo(variable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VariableDTO.class);
        VariableDTO variableDTO1 = new VariableDTO();
        variableDTO1.setId(1L);
        VariableDTO variableDTO2 = new VariableDTO();
        assertThat(variableDTO1).isNotEqualTo(variableDTO2);
        variableDTO2.setId(variableDTO1.getId());
        assertThat(variableDTO1).isEqualTo(variableDTO2);
        variableDTO2.setId(2L);
        assertThat(variableDTO1).isNotEqualTo(variableDTO2);
        variableDTO1.setId(null);
        assertThat(variableDTO1).isNotEqualTo(variableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(variableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(variableMapper.fromId(null)).isNull();
    }
}
