package ml.danielcordero.tests.aplicacion.web.rest;

import ml.danielcordero.tests.aplicacion.AplicacionApp;

import ml.danielcordero.tests.aplicacion.domain.ConfiguracionVariable;
import ml.danielcordero.tests.aplicacion.repository.ConfiguracionVariableRepository;
import ml.danielcordero.tests.aplicacion.service.ConfiguracionVariableService;
import ml.danielcordero.tests.aplicacion.service.dto.ConfiguracionVariableDTO;
import ml.danielcordero.tests.aplicacion.service.mapper.ConfiguracionVariableMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static ml.danielcordero.tests.aplicacion.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConfiguracionVariableResource REST controller.
 *
 * @see ConfiguracionVariableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AplicacionApp.class)
public class ConfiguracionVariableResourceIntTest {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_VECES = 1;
    private static final Integer UPDATED_VECES = 2;

    private static final Integer DEFAULT_OTROCAMPO = 1;
    private static final Integer UPDATED_OTROCAMPO = 2;

    private static final String DEFAULT_CAMPOADICIONAL = "AAAAAAAAAA";
    private static final String UPDATED_CAMPOADICIONAL = "BBBBBBBBBB";

    @Autowired
    private ConfiguracionVariableRepository configuracionVariableRepository;


    @Autowired
    private ConfiguracionVariableMapper configuracionVariableMapper;
    

    @Autowired
    private ConfiguracionVariableService configuracionVariableService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfiguracionVariableMockMvc;

    private ConfiguracionVariable configuracionVariable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfiguracionVariableResource configuracionVariableResource = new ConfiguracionVariableResource(configuracionVariableService);
        this.restConfiguracionVariableMockMvc = MockMvcBuilders.standaloneSetup(configuracionVariableResource)
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
    public static ConfiguracionVariable createEntity(EntityManager em) {
        ConfiguracionVariable configuracionVariable = new ConfiguracionVariable()
            .fecha(DEFAULT_FECHA)
            .veces(DEFAULT_VECES)
            .otrocampo(DEFAULT_OTROCAMPO)
            .campoadicional(DEFAULT_CAMPOADICIONAL);
        return configuracionVariable;
    }

    @Before
    public void initTest() {
        configuracionVariable = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfiguracionVariable() throws Exception {
        int databaseSizeBeforeCreate = configuracionVariableRepository.findAll().size();

        // Create the ConfiguracionVariable
        ConfiguracionVariableDTO configuracionVariableDTO = configuracionVariableMapper.toDto(configuracionVariable);
        restConfiguracionVariableMockMvc.perform(post("/api/configuracion-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracionVariableDTO)))
            .andExpect(status().isCreated());

        // Validate the ConfiguracionVariable in the database
        List<ConfiguracionVariable> configuracionVariableList = configuracionVariableRepository.findAll();
        assertThat(configuracionVariableList).hasSize(databaseSizeBeforeCreate + 1);
        ConfiguracionVariable testConfiguracionVariable = configuracionVariableList.get(configuracionVariableList.size() - 1);
        assertThat(testConfiguracionVariable.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testConfiguracionVariable.getVeces()).isEqualTo(DEFAULT_VECES);
        assertThat(testConfiguracionVariable.getOtrocampo()).isEqualTo(DEFAULT_OTROCAMPO);
        assertThat(testConfiguracionVariable.getCampoadicional()).isEqualTo(DEFAULT_CAMPOADICIONAL);
    }

    @Test
    @Transactional
    public void createConfiguracionVariableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configuracionVariableRepository.findAll().size();

        // Create the ConfiguracionVariable with an existing ID
        configuracionVariable.setId(1L);
        ConfiguracionVariableDTO configuracionVariableDTO = configuracionVariableMapper.toDto(configuracionVariable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfiguracionVariableMockMvc.perform(post("/api/configuracion-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracionVariableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfiguracionVariable in the database
        List<ConfiguracionVariable> configuracionVariableList = configuracionVariableRepository.findAll();
        assertThat(configuracionVariableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfiguracionVariables() throws Exception {
        // Initialize the database
        configuracionVariableRepository.saveAndFlush(configuracionVariable);

        // Get all the configuracionVariableList
        restConfiguracionVariableMockMvc.perform(get("/api/configuracion-variables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configuracionVariable.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].veces").value(hasItem(DEFAULT_VECES)))
            .andExpect(jsonPath("$.[*].otrocampo").value(hasItem(DEFAULT_OTROCAMPO)))
            .andExpect(jsonPath("$.[*].campoadicional").value(hasItem(DEFAULT_CAMPOADICIONAL.toString())));
    }
    

    @Test
    @Transactional
    public void getConfiguracionVariable() throws Exception {
        // Initialize the database
        configuracionVariableRepository.saveAndFlush(configuracionVariable);

        // Get the configuracionVariable
        restConfiguracionVariableMockMvc.perform(get("/api/configuracion-variables/{id}", configuracionVariable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configuracionVariable.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.veces").value(DEFAULT_VECES))
            .andExpect(jsonPath("$.otrocampo").value(DEFAULT_OTROCAMPO))
            .andExpect(jsonPath("$.campoadicional").value(DEFAULT_CAMPOADICIONAL.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingConfiguracionVariable() throws Exception {
        // Get the configuracionVariable
        restConfiguracionVariableMockMvc.perform(get("/api/configuracion-variables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfiguracionVariable() throws Exception {
        // Initialize the database
        configuracionVariableRepository.saveAndFlush(configuracionVariable);

        int databaseSizeBeforeUpdate = configuracionVariableRepository.findAll().size();

        // Update the configuracionVariable
        ConfiguracionVariable updatedConfiguracionVariable = configuracionVariableRepository.findById(configuracionVariable.getId()).get();
        // Disconnect from session so that the updates on updatedConfiguracionVariable are not directly saved in db
        em.detach(updatedConfiguracionVariable);
        updatedConfiguracionVariable
            .fecha(UPDATED_FECHA)
            .veces(UPDATED_VECES)
            .otrocampo(UPDATED_OTROCAMPO)
            .campoadicional(UPDATED_CAMPOADICIONAL);
        ConfiguracionVariableDTO configuracionVariableDTO = configuracionVariableMapper.toDto(updatedConfiguracionVariable);

        restConfiguracionVariableMockMvc.perform(put("/api/configuracion-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracionVariableDTO)))
            .andExpect(status().isOk());

        // Validate the ConfiguracionVariable in the database
        List<ConfiguracionVariable> configuracionVariableList = configuracionVariableRepository.findAll();
        assertThat(configuracionVariableList).hasSize(databaseSizeBeforeUpdate);
        ConfiguracionVariable testConfiguracionVariable = configuracionVariableList.get(configuracionVariableList.size() - 1);
        assertThat(testConfiguracionVariable.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testConfiguracionVariable.getVeces()).isEqualTo(UPDATED_VECES);
        assertThat(testConfiguracionVariable.getOtrocampo()).isEqualTo(UPDATED_OTROCAMPO);
        assertThat(testConfiguracionVariable.getCampoadicional()).isEqualTo(UPDATED_CAMPOADICIONAL);
    }

    @Test
    @Transactional
    public void updateNonExistingConfiguracionVariable() throws Exception {
        int databaseSizeBeforeUpdate = configuracionVariableRepository.findAll().size();

        // Create the ConfiguracionVariable
        ConfiguracionVariableDTO configuracionVariableDTO = configuracionVariableMapper.toDto(configuracionVariable);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restConfiguracionVariableMockMvc.perform(put("/api/configuracion-variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracionVariableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConfiguracionVariable in the database
        List<ConfiguracionVariable> configuracionVariableList = configuracionVariableRepository.findAll();
        assertThat(configuracionVariableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfiguracionVariable() throws Exception {
        // Initialize the database
        configuracionVariableRepository.saveAndFlush(configuracionVariable);

        int databaseSizeBeforeDelete = configuracionVariableRepository.findAll().size();

        // Get the configuracionVariable
        restConfiguracionVariableMockMvc.perform(delete("/api/configuracion-variables/{id}", configuracionVariable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConfiguracionVariable> configuracionVariableList = configuracionVariableRepository.findAll();
        assertThat(configuracionVariableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfiguracionVariable.class);
        ConfiguracionVariable configuracionVariable1 = new ConfiguracionVariable();
        configuracionVariable1.setId(1L);
        ConfiguracionVariable configuracionVariable2 = new ConfiguracionVariable();
        configuracionVariable2.setId(configuracionVariable1.getId());
        assertThat(configuracionVariable1).isEqualTo(configuracionVariable2);
        configuracionVariable2.setId(2L);
        assertThat(configuracionVariable1).isNotEqualTo(configuracionVariable2);
        configuracionVariable1.setId(null);
        assertThat(configuracionVariable1).isNotEqualTo(configuracionVariable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfiguracionVariableDTO.class);
        ConfiguracionVariableDTO configuracionVariableDTO1 = new ConfiguracionVariableDTO();
        configuracionVariableDTO1.setId(1L);
        ConfiguracionVariableDTO configuracionVariableDTO2 = new ConfiguracionVariableDTO();
        assertThat(configuracionVariableDTO1).isNotEqualTo(configuracionVariableDTO2);
        configuracionVariableDTO2.setId(configuracionVariableDTO1.getId());
        assertThat(configuracionVariableDTO1).isEqualTo(configuracionVariableDTO2);
        configuracionVariableDTO2.setId(2L);
        assertThat(configuracionVariableDTO1).isNotEqualTo(configuracionVariableDTO2);
        configuracionVariableDTO1.setId(null);
        assertThat(configuracionVariableDTO1).isNotEqualTo(configuracionVariableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(configuracionVariableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(configuracionVariableMapper.fromId(null)).isNull();
    }
}
