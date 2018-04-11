package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackendApp;

import com.mycompany.myapp.domain.DaySechudlerInfo;
import com.mycompany.myapp.repository.DaySechudlerInfoRepository;
import com.mycompany.myapp.service.DaySechudlerInfoService;
import com.mycompany.myapp.service.dto.DaySechudlerInfoDTO;
import com.mycompany.myapp.service.mapper.DaySechudlerInfoMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DaySechudlerInfoResource REST controller.
 *
 * @see DaySechudlerInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class DaySechudlerInfoResourceIntTest {

    private static final Long DEFAULT_TUAN_ID = 1L;
    private static final Long UPDATED_TUAN_ID = 2L;

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_WEATHER = "AAAAAAAAAA";
    private static final String UPDATED_WEATHER = "BBBBBBBBBB";

    private static final String DEFAULT_DAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DAY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOOD = "AAAAAAAAAA";
    private static final String UPDATED_MOOD = "BBBBBBBBBB";

    private static final String DEFAULT_AM_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_AM_SCHEDULE = "BBBBBBBBBB";

    private static final String DEFAULT_PM_SCHEDULE = "AAAAAAAAAA";
    private static final String UPDATED_PM_SCHEDULE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DaySechudlerInfoRepository daySechudlerInfoRepository;

    @Autowired
    private DaySechudlerInfoMapper daySechudlerInfoMapper;

    @Autowired
    private DaySechudlerInfoService daySechudlerInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDaySechudlerInfoMockMvc;

    private DaySechudlerInfo daySechudlerInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DaySechudlerInfoResource daySechudlerInfoResource = new DaySechudlerInfoResource(daySechudlerInfoService);
        this.restDaySechudlerInfoMockMvc = MockMvcBuilders.standaloneSetup(daySechudlerInfoResource)
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
    public static DaySechudlerInfo createEntity(EntityManager em) {
        DaySechudlerInfo daySechudlerInfo = new DaySechudlerInfo()
            .tuanId(DEFAULT_TUAN_ID)
            .time(DEFAULT_TIME)
            .weather(DEFAULT_WEATHER)
            .dayName(DEFAULT_DAY_NAME)
            .mood(DEFAULT_MOOD)
            .amSchedule(DEFAULT_AM_SCHEDULE)
            .pmSchedule(DEFAULT_PM_SCHEDULE)
            .creator(DEFAULT_CREATOR)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return daySechudlerInfo;
    }

    @Before
    public void initTest() {
        daySechudlerInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createDaySechudlerInfo() throws Exception {
        int databaseSizeBeforeCreate = daySechudlerInfoRepository.findAll().size();

        // Create the DaySechudlerInfo
        DaySechudlerInfoDTO daySechudlerInfoDTO = daySechudlerInfoMapper.toDto(daySechudlerInfo);
        restDaySechudlerInfoMockMvc.perform(post("/api/day-sechudler-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(daySechudlerInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the DaySechudlerInfo in the database
        List<DaySechudlerInfo> daySechudlerInfoList = daySechudlerInfoRepository.findAll();
        assertThat(daySechudlerInfoList).hasSize(databaseSizeBeforeCreate + 1);
        DaySechudlerInfo testDaySechudlerInfo = daySechudlerInfoList.get(daySechudlerInfoList.size() - 1);
        assertThat(testDaySechudlerInfo.getTuanId()).isEqualTo(DEFAULT_TUAN_ID);
        assertThat(testDaySechudlerInfo.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testDaySechudlerInfo.getWeather()).isEqualTo(DEFAULT_WEATHER);
        assertThat(testDaySechudlerInfo.getDayName()).isEqualTo(DEFAULT_DAY_NAME);
        assertThat(testDaySechudlerInfo.getMood()).isEqualTo(DEFAULT_MOOD);
        assertThat(testDaySechudlerInfo.getAmSchedule()).isEqualTo(DEFAULT_AM_SCHEDULE);
        assertThat(testDaySechudlerInfo.getPmSchedule()).isEqualTo(DEFAULT_PM_SCHEDULE);
        assertThat(testDaySechudlerInfo.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDaySechudlerInfo.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testDaySechudlerInfo.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createDaySechudlerInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = daySechudlerInfoRepository.findAll().size();

        // Create the DaySechudlerInfo with an existing ID
        daySechudlerInfo.setId(1L);
        DaySechudlerInfoDTO daySechudlerInfoDTO = daySechudlerInfoMapper.toDto(daySechudlerInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDaySechudlerInfoMockMvc.perform(post("/api/day-sechudler-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(daySechudlerInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DaySechudlerInfo in the database
        List<DaySechudlerInfo> daySechudlerInfoList = daySechudlerInfoRepository.findAll();
        assertThat(daySechudlerInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDaySechudlerInfos() throws Exception {
        // Initialize the database
        daySechudlerInfoRepository.saveAndFlush(daySechudlerInfo);

        // Get all the daySechudlerInfoList
        restDaySechudlerInfoMockMvc.perform(get("/api/day-sechudler-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(daySechudlerInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].tuanId").value(hasItem(DEFAULT_TUAN_ID.intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].weather").value(hasItem(DEFAULT_WEATHER.toString())))
            .andExpect(jsonPath("$.[*].dayName").value(hasItem(DEFAULT_DAY_NAME.toString())))
            .andExpect(jsonPath("$.[*].mood").value(hasItem(DEFAULT_MOOD.toString())))
            .andExpect(jsonPath("$.[*].amSchedule").value(hasItem(DEFAULT_AM_SCHEDULE.toString())))
            .andExpect(jsonPath("$.[*].pmSchedule").value(hasItem(DEFAULT_PM_SCHEDULE.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }

    @Test
    @Transactional
    public void getDaySechudlerInfo() throws Exception {
        // Initialize the database
        daySechudlerInfoRepository.saveAndFlush(daySechudlerInfo);

        // Get the daySechudlerInfo
        restDaySechudlerInfoMockMvc.perform(get("/api/day-sechudler-infos/{id}", daySechudlerInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(daySechudlerInfo.getId().intValue()))
            .andExpect(jsonPath("$.tuanId").value(DEFAULT_TUAN_ID.intValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.weather").value(DEFAULT_WEATHER.toString()))
            .andExpect(jsonPath("$.dayName").value(DEFAULT_DAY_NAME.toString()))
            .andExpect(jsonPath("$.mood").value(DEFAULT_MOOD.toString()))
            .andExpect(jsonPath("$.amSchedule").value(DEFAULT_AM_SCHEDULE.toString()))
            .andExpect(jsonPath("$.pmSchedule").value(DEFAULT_PM_SCHEDULE.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDaySechudlerInfo() throws Exception {
        // Get the daySechudlerInfo
        restDaySechudlerInfoMockMvc.perform(get("/api/day-sechudler-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDaySechudlerInfo() throws Exception {
        // Initialize the database
        daySechudlerInfoRepository.saveAndFlush(daySechudlerInfo);
        int databaseSizeBeforeUpdate = daySechudlerInfoRepository.findAll().size();

        // Update the daySechudlerInfo
        DaySechudlerInfo updatedDaySechudlerInfo = daySechudlerInfoRepository.findOne(daySechudlerInfo.getId());
        // Disconnect from session so that the updates on updatedDaySechudlerInfo are not directly saved in db
        em.detach(updatedDaySechudlerInfo);
        updatedDaySechudlerInfo
            .tuanId(UPDATED_TUAN_ID)
            .time(UPDATED_TIME)
            .weather(UPDATED_WEATHER)
            .dayName(UPDATED_DAY_NAME)
            .mood(UPDATED_MOOD)
            .amSchedule(UPDATED_AM_SCHEDULE)
            .pmSchedule(UPDATED_PM_SCHEDULE)
            .creator(UPDATED_CREATOR)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE);
        DaySechudlerInfoDTO daySechudlerInfoDTO = daySechudlerInfoMapper.toDto(updatedDaySechudlerInfo);

        restDaySechudlerInfoMockMvc.perform(put("/api/day-sechudler-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(daySechudlerInfoDTO)))
            .andExpect(status().isOk());

        // Validate the DaySechudlerInfo in the database
        List<DaySechudlerInfo> daySechudlerInfoList = daySechudlerInfoRepository.findAll();
        assertThat(daySechudlerInfoList).hasSize(databaseSizeBeforeUpdate);
        DaySechudlerInfo testDaySechudlerInfo = daySechudlerInfoList.get(daySechudlerInfoList.size() - 1);
        assertThat(testDaySechudlerInfo.getTuanId()).isEqualTo(UPDATED_TUAN_ID);
        assertThat(testDaySechudlerInfo.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testDaySechudlerInfo.getWeather()).isEqualTo(UPDATED_WEATHER);
        assertThat(testDaySechudlerInfo.getDayName()).isEqualTo(UPDATED_DAY_NAME);
        assertThat(testDaySechudlerInfo.getMood()).isEqualTo(UPDATED_MOOD);
        assertThat(testDaySechudlerInfo.getAmSchedule()).isEqualTo(UPDATED_AM_SCHEDULE);
        assertThat(testDaySechudlerInfo.getPmSchedule()).isEqualTo(UPDATED_PM_SCHEDULE);
        assertThat(testDaySechudlerInfo.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDaySechudlerInfo.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testDaySechudlerInfo.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDaySechudlerInfo() throws Exception {
        int databaseSizeBeforeUpdate = daySechudlerInfoRepository.findAll().size();

        // Create the DaySechudlerInfo
        DaySechudlerInfoDTO daySechudlerInfoDTO = daySechudlerInfoMapper.toDto(daySechudlerInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDaySechudlerInfoMockMvc.perform(put("/api/day-sechudler-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(daySechudlerInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the DaySechudlerInfo in the database
        List<DaySechudlerInfo> daySechudlerInfoList = daySechudlerInfoRepository.findAll();
        assertThat(daySechudlerInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDaySechudlerInfo() throws Exception {
        // Initialize the database
        daySechudlerInfoRepository.saveAndFlush(daySechudlerInfo);
        int databaseSizeBeforeDelete = daySechudlerInfoRepository.findAll().size();

        // Get the daySechudlerInfo
        restDaySechudlerInfoMockMvc.perform(delete("/api/day-sechudler-infos/{id}", daySechudlerInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DaySechudlerInfo> daySechudlerInfoList = daySechudlerInfoRepository.findAll();
        assertThat(daySechudlerInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DaySechudlerInfo.class);
        DaySechudlerInfo daySechudlerInfo1 = new DaySechudlerInfo();
        daySechudlerInfo1.setId(1L);
        DaySechudlerInfo daySechudlerInfo2 = new DaySechudlerInfo();
        daySechudlerInfo2.setId(daySechudlerInfo1.getId());
        assertThat(daySechudlerInfo1).isEqualTo(daySechudlerInfo2);
        daySechudlerInfo2.setId(2L);
        assertThat(daySechudlerInfo1).isNotEqualTo(daySechudlerInfo2);
        daySechudlerInfo1.setId(null);
        assertThat(daySechudlerInfo1).isNotEqualTo(daySechudlerInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DaySechudlerInfoDTO.class);
        DaySechudlerInfoDTO daySechudlerInfoDTO1 = new DaySechudlerInfoDTO();
        daySechudlerInfoDTO1.setId(1L);
        DaySechudlerInfoDTO daySechudlerInfoDTO2 = new DaySechudlerInfoDTO();
        assertThat(daySechudlerInfoDTO1).isNotEqualTo(daySechudlerInfoDTO2);
        daySechudlerInfoDTO2.setId(daySechudlerInfoDTO1.getId());
        assertThat(daySechudlerInfoDTO1).isEqualTo(daySechudlerInfoDTO2);
        daySechudlerInfoDTO2.setId(2L);
        assertThat(daySechudlerInfoDTO1).isNotEqualTo(daySechudlerInfoDTO2);
        daySechudlerInfoDTO1.setId(null);
        assertThat(daySechudlerInfoDTO1).isNotEqualTo(daySechudlerInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(daySechudlerInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(daySechudlerInfoMapper.fromId(null)).isNull();
    }
}
