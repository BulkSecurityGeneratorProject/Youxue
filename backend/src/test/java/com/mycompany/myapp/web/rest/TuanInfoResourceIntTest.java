package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackendApp;

import com.mycompany.myapp.domain.TuanInfo;
import com.mycompany.myapp.repository.TuanInfoRepository;
import com.mycompany.myapp.service.TuanInfoService;
import com.mycompany.myapp.service.dto.TuanInfoDTO;
import com.mycompany.myapp.service.mapper.TuanInfoMapper;
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
 * Test class for the TuanInfoResource REST controller.
 *
 * @see TuanInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class TuanInfoResourceIntTest {

    private static final String DEFAULT_LEADER = "AAAAAAAAAA";
    private static final String UPDATED_LEADER = "BBBBBBBBBB";

    private static final String DEFAULT_TUAN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TUAN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TUAN_DESCRPTION = "AAAAAAAAAA";
    private static final String UPDATED_TUAN_DESCRPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Long DEFAULT_TEAM_MEMBER_COUNT = 1L;
    private static final Long UPDATED_TEAM_MEMBER_COUNT = 2L;

    private static final Instant DEFAULT_TUAN_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TUAN_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TUAN_END_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TUAN_END_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_TUAN_CREAT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TUAN_CREAT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TuanInfoRepository tuanInfoRepository;

    @Autowired
    private TuanInfoMapper tuanInfoMapper;

    @Autowired
    private TuanInfoService tuanInfoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTuanInfoMockMvc;

    private TuanInfo tuanInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TuanInfoResource tuanInfoResource = new TuanInfoResource(tuanInfoService);
        this.restTuanInfoMockMvc = MockMvcBuilders.standaloneSetup(tuanInfoResource)
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
    public static TuanInfo createEntity(EntityManager em) {
        TuanInfo tuanInfo = new TuanInfo()
            .leader(DEFAULT_LEADER)
            .tuanName(DEFAULT_TUAN_NAME)
            .tuanDescrption(DEFAULT_TUAN_DESCRPTION)
            .city(DEFAULT_CITY)
            .country(DEFAULT_COUNTRY)
            .teamMemberCount(DEFAULT_TEAM_MEMBER_COUNT)
            .tuanStartTime(DEFAULT_TUAN_START_TIME)
            .tuanEndTime(DEFAULT_TUAN_END_TIME)
            .tuanCreatTime(DEFAULT_TUAN_CREAT_TIME)
            .creator(DEFAULT_CREATOR)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return tuanInfo;
    }

    @Before
    public void initTest() {
        tuanInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTuanInfo() throws Exception {
        int databaseSizeBeforeCreate = tuanInfoRepository.findAll().size();

        // Create the TuanInfo
        TuanInfoDTO tuanInfoDTO = tuanInfoMapper.toDto(tuanInfo);
        restTuanInfoMockMvc.perform(post("/api/tuan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the TuanInfo in the database
        List<TuanInfo> tuanInfoList = tuanInfoRepository.findAll();
        assertThat(tuanInfoList).hasSize(databaseSizeBeforeCreate + 1);
        TuanInfo testTuanInfo = tuanInfoList.get(tuanInfoList.size() - 1);
        assertThat(testTuanInfo.getLeader()).isEqualTo(DEFAULT_LEADER);
        assertThat(testTuanInfo.getTuanName()).isEqualTo(DEFAULT_TUAN_NAME);
        assertThat(testTuanInfo.getTuanDescrption()).isEqualTo(DEFAULT_TUAN_DESCRPTION);
        assertThat(testTuanInfo.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testTuanInfo.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testTuanInfo.getTeamMemberCount()).isEqualTo(DEFAULT_TEAM_MEMBER_COUNT);
        assertThat(testTuanInfo.getTuanStartTime()).isEqualTo(DEFAULT_TUAN_START_TIME);
        assertThat(testTuanInfo.getTuanEndTime()).isEqualTo(DEFAULT_TUAN_END_TIME);
        assertThat(testTuanInfo.getTuanCreatTime()).isEqualTo(DEFAULT_TUAN_CREAT_TIME);
        assertThat(testTuanInfo.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testTuanInfo.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTuanInfo.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createTuanInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tuanInfoRepository.findAll().size();

        // Create the TuanInfo with an existing ID
        tuanInfo.setId(1L);
        TuanInfoDTO tuanInfoDTO = tuanInfoMapper.toDto(tuanInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTuanInfoMockMvc.perform(post("/api/tuan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TuanInfo in the database
        List<TuanInfo> tuanInfoList = tuanInfoRepository.findAll();
        assertThat(tuanInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTuanInfos() throws Exception {
        // Initialize the database
        tuanInfoRepository.saveAndFlush(tuanInfo);

        // Get all the tuanInfoList
        restTuanInfoMockMvc.perform(get("/api/tuan-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tuanInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].leader").value(hasItem(DEFAULT_LEADER.toString())))
            .andExpect(jsonPath("$.[*].tuanName").value(hasItem(DEFAULT_TUAN_NAME.toString())))
            .andExpect(jsonPath("$.[*].tuanDescrption").value(hasItem(DEFAULT_TUAN_DESCRPTION.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].teamMemberCount").value(hasItem(DEFAULT_TEAM_MEMBER_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].tuanStartTime").value(hasItem(DEFAULT_TUAN_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].tuanEndTime").value(hasItem(DEFAULT_TUAN_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].tuanCreatTime").value(hasItem(DEFAULT_TUAN_CREAT_TIME.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }

    @Test
    @Transactional
    public void getTuanInfo() throws Exception {
        // Initialize the database
        tuanInfoRepository.saveAndFlush(tuanInfo);

        // Get the tuanInfo
        restTuanInfoMockMvc.perform(get("/api/tuan-infos/{id}", tuanInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tuanInfo.getId().intValue()))
            .andExpect(jsonPath("$.leader").value(DEFAULT_LEADER.toString()))
            .andExpect(jsonPath("$.tuanName").value(DEFAULT_TUAN_NAME.toString()))
            .andExpect(jsonPath("$.tuanDescrption").value(DEFAULT_TUAN_DESCRPTION.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.teamMemberCount").value(DEFAULT_TEAM_MEMBER_COUNT.intValue()))
            .andExpect(jsonPath("$.tuanStartTime").value(DEFAULT_TUAN_START_TIME.toString()))
            .andExpect(jsonPath("$.tuanEndTime").value(DEFAULT_TUAN_END_TIME.toString()))
            .andExpect(jsonPath("$.tuanCreatTime").value(DEFAULT_TUAN_CREAT_TIME.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTuanInfo() throws Exception {
        // Get the tuanInfo
        restTuanInfoMockMvc.perform(get("/api/tuan-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTuanInfo() throws Exception {
        // Initialize the database
        tuanInfoRepository.saveAndFlush(tuanInfo);
        int databaseSizeBeforeUpdate = tuanInfoRepository.findAll().size();

        // Update the tuanInfo
        TuanInfo updatedTuanInfo = tuanInfoRepository.findOne(tuanInfo.getId());
        // Disconnect from session so that the updates on updatedTuanInfo are not directly saved in db
        em.detach(updatedTuanInfo);
        updatedTuanInfo
            .leader(UPDATED_LEADER)
            .tuanName(UPDATED_TUAN_NAME)
            .tuanDescrption(UPDATED_TUAN_DESCRPTION)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .teamMemberCount(UPDATED_TEAM_MEMBER_COUNT)
            .tuanStartTime(UPDATED_TUAN_START_TIME)
            .tuanEndTime(UPDATED_TUAN_END_TIME)
            .tuanCreatTime(UPDATED_TUAN_CREAT_TIME)
            .creator(UPDATED_CREATOR)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE);
        TuanInfoDTO tuanInfoDTO = tuanInfoMapper.toDto(updatedTuanInfo);

        restTuanInfoMockMvc.perform(put("/api/tuan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanInfoDTO)))
            .andExpect(status().isOk());

        // Validate the TuanInfo in the database
        List<TuanInfo> tuanInfoList = tuanInfoRepository.findAll();
        assertThat(tuanInfoList).hasSize(databaseSizeBeforeUpdate);
        TuanInfo testTuanInfo = tuanInfoList.get(tuanInfoList.size() - 1);
        assertThat(testTuanInfo.getLeader()).isEqualTo(UPDATED_LEADER);
        assertThat(testTuanInfo.getTuanName()).isEqualTo(UPDATED_TUAN_NAME);
        assertThat(testTuanInfo.getTuanDescrption()).isEqualTo(UPDATED_TUAN_DESCRPTION);
        assertThat(testTuanInfo.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testTuanInfo.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testTuanInfo.getTeamMemberCount()).isEqualTo(UPDATED_TEAM_MEMBER_COUNT);
        assertThat(testTuanInfo.getTuanStartTime()).isEqualTo(UPDATED_TUAN_START_TIME);
        assertThat(testTuanInfo.getTuanEndTime()).isEqualTo(UPDATED_TUAN_END_TIME);
        assertThat(testTuanInfo.getTuanCreatTime()).isEqualTo(UPDATED_TUAN_CREAT_TIME);
        assertThat(testTuanInfo.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testTuanInfo.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTuanInfo.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingTuanInfo() throws Exception {
        int databaseSizeBeforeUpdate = tuanInfoRepository.findAll().size();

        // Create the TuanInfo
        TuanInfoDTO tuanInfoDTO = tuanInfoMapper.toDto(tuanInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTuanInfoMockMvc.perform(put("/api/tuan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the TuanInfo in the database
        List<TuanInfo> tuanInfoList = tuanInfoRepository.findAll();
        assertThat(tuanInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTuanInfo() throws Exception {
        // Initialize the database
        tuanInfoRepository.saveAndFlush(tuanInfo);
        int databaseSizeBeforeDelete = tuanInfoRepository.findAll().size();

        // Get the tuanInfo
        restTuanInfoMockMvc.perform(delete("/api/tuan-infos/{id}", tuanInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TuanInfo> tuanInfoList = tuanInfoRepository.findAll();
        assertThat(tuanInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TuanInfo.class);
        TuanInfo tuanInfo1 = new TuanInfo();
        tuanInfo1.setId(1L);
        TuanInfo tuanInfo2 = new TuanInfo();
        tuanInfo2.setId(tuanInfo1.getId());
        assertThat(tuanInfo1).isEqualTo(tuanInfo2);
        tuanInfo2.setId(2L);
        assertThat(tuanInfo1).isNotEqualTo(tuanInfo2);
        tuanInfo1.setId(null);
        assertThat(tuanInfo1).isNotEqualTo(tuanInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TuanInfoDTO.class);
        TuanInfoDTO tuanInfoDTO1 = new TuanInfoDTO();
        tuanInfoDTO1.setId(1L);
        TuanInfoDTO tuanInfoDTO2 = new TuanInfoDTO();
        assertThat(tuanInfoDTO1).isNotEqualTo(tuanInfoDTO2);
        tuanInfoDTO2.setId(tuanInfoDTO1.getId());
        assertThat(tuanInfoDTO1).isEqualTo(tuanInfoDTO2);
        tuanInfoDTO2.setId(2L);
        assertThat(tuanInfoDTO1).isNotEqualTo(tuanInfoDTO2);
        tuanInfoDTO1.setId(null);
        assertThat(tuanInfoDTO1).isNotEqualTo(tuanInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tuanInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tuanInfoMapper.fromId(null)).isNull();
    }
}
