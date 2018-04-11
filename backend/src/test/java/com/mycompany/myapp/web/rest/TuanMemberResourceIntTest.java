package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackendApp;

import com.mycompany.myapp.domain.TuanMember;
import com.mycompany.myapp.repository.TuanMemberRepository;
import com.mycompany.myapp.service.TuanMemberService;
import com.mycompany.myapp.service.dto.TuanMemberDTO;
import com.mycompany.myapp.service.mapper.TuanMemberMapper;
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
 * Test class for the TuanMemberResource REST controller.
 *
 * @see TuanMemberResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class TuanMemberResourceIntTest {

    private static final Long DEFAULT_TUAN_ID = 1L;
    private static final Long UPDATED_TUAN_ID = 2L;

    private static final String DEFAULT_MEMBER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBER_DESCRPTION = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_DESCRPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_YEARS_OLD = 1L;
    private static final Long UPDATED_YEARS_OLD = 2L;

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final String DEFAULT_FROM_CITY = "AAAAAAAAAA";
    private static final String UPDATED_FROM_CITY = "BBBBBBBBBB";

    private static final Instant DEFAULT_JOIN_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOIN_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private TuanMemberRepository tuanMemberRepository;

    @Autowired
    private TuanMemberMapper tuanMemberMapper;

    @Autowired
    private TuanMemberService tuanMemberService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTuanMemberMockMvc;

    private TuanMember tuanMember;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TuanMemberResource tuanMemberResource = new TuanMemberResource(tuanMemberService);
        this.restTuanMemberMockMvc = MockMvcBuilders.standaloneSetup(tuanMemberResource)
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
    public static TuanMember createEntity(EntityManager em) {
        TuanMember tuanMember = new TuanMember()
            .tuanId(DEFAULT_TUAN_ID)
            .memberType(DEFAULT_MEMBER_TYPE)
            .memberDescrption(DEFAULT_MEMBER_DESCRPTION)
            .yearsOld(DEFAULT_YEARS_OLD)
            .sex(DEFAULT_SEX)
            .fromCity(DEFAULT_FROM_CITY)
            .joinTime(DEFAULT_JOIN_TIME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .creator(DEFAULT_CREATOR)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return tuanMember;
    }

    @Before
    public void initTest() {
        tuanMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createTuanMember() throws Exception {
        int databaseSizeBeforeCreate = tuanMemberRepository.findAll().size();

        // Create the TuanMember
        TuanMemberDTO tuanMemberDTO = tuanMemberMapper.toDto(tuanMember);
        restTuanMemberMockMvc.perform(post("/api/tuan-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the TuanMember in the database
        List<TuanMember> tuanMemberList = tuanMemberRepository.findAll();
        assertThat(tuanMemberList).hasSize(databaseSizeBeforeCreate + 1);
        TuanMember testTuanMember = tuanMemberList.get(tuanMemberList.size() - 1);
        assertThat(testTuanMember.getTuanId()).isEqualTo(DEFAULT_TUAN_ID);
        assertThat(testTuanMember.getMemberType()).isEqualTo(DEFAULT_MEMBER_TYPE);
        assertThat(testTuanMember.getMemberDescrption()).isEqualTo(DEFAULT_MEMBER_DESCRPTION);
        assertThat(testTuanMember.getYearsOld()).isEqualTo(DEFAULT_YEARS_OLD);
        assertThat(testTuanMember.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testTuanMember.getFromCity()).isEqualTo(DEFAULT_FROM_CITY);
        assertThat(testTuanMember.getJoinTime()).isEqualTo(DEFAULT_JOIN_TIME);
        assertThat(testTuanMember.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTuanMember.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testTuanMember.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testTuanMember.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTuanMember.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createTuanMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tuanMemberRepository.findAll().size();

        // Create the TuanMember with an existing ID
        tuanMember.setId(1L);
        TuanMemberDTO tuanMemberDTO = tuanMemberMapper.toDto(tuanMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTuanMemberMockMvc.perform(post("/api/tuan-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TuanMember in the database
        List<TuanMember> tuanMemberList = tuanMemberRepository.findAll();
        assertThat(tuanMemberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTuanMembers() throws Exception {
        // Initialize the database
        tuanMemberRepository.saveAndFlush(tuanMember);

        // Get all the tuanMemberList
        restTuanMemberMockMvc.perform(get("/api/tuan-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tuanMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].tuanId").value(hasItem(DEFAULT_TUAN_ID.intValue())))
            .andExpect(jsonPath("$.[*].memberType").value(hasItem(DEFAULT_MEMBER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].memberDescrption").value(hasItem(DEFAULT_MEMBER_DESCRPTION.toString())))
            .andExpect(jsonPath("$.[*].yearsOld").value(hasItem(DEFAULT_YEARS_OLD.intValue())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].fromCity").value(hasItem(DEFAULT_FROM_CITY.toString())))
            .andExpect(jsonPath("$.[*].joinTime").value(hasItem(DEFAULT_JOIN_TIME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }

    @Test
    @Transactional
    public void getTuanMember() throws Exception {
        // Initialize the database
        tuanMemberRepository.saveAndFlush(tuanMember);

        // Get the tuanMember
        restTuanMemberMockMvc.perform(get("/api/tuan-members/{id}", tuanMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tuanMember.getId().intValue()))
            .andExpect(jsonPath("$.tuanId").value(DEFAULT_TUAN_ID.intValue()))
            .andExpect(jsonPath("$.memberType").value(DEFAULT_MEMBER_TYPE.toString()))
            .andExpect(jsonPath("$.memberDescrption").value(DEFAULT_MEMBER_DESCRPTION.toString()))
            .andExpect(jsonPath("$.yearsOld").value(DEFAULT_YEARS_OLD.intValue()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.fromCity").value(DEFAULT_FROM_CITY.toString()))
            .andExpect(jsonPath("$.joinTime").value(DEFAULT_JOIN_TIME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTuanMember() throws Exception {
        // Get the tuanMember
        restTuanMemberMockMvc.perform(get("/api/tuan-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTuanMember() throws Exception {
        // Initialize the database
        tuanMemberRepository.saveAndFlush(tuanMember);
        int databaseSizeBeforeUpdate = tuanMemberRepository.findAll().size();

        // Update the tuanMember
        TuanMember updatedTuanMember = tuanMemberRepository.findOne(tuanMember.getId());
        // Disconnect from session so that the updates on updatedTuanMember are not directly saved in db
        em.detach(updatedTuanMember);
        updatedTuanMember
            .tuanId(UPDATED_TUAN_ID)
            .memberType(UPDATED_MEMBER_TYPE)
            .memberDescrption(UPDATED_MEMBER_DESCRPTION)
            .yearsOld(UPDATED_YEARS_OLD)
            .sex(UPDATED_SEX)
            .fromCity(UPDATED_FROM_CITY)
            .joinTime(UPDATED_JOIN_TIME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .creator(UPDATED_CREATOR)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE);
        TuanMemberDTO tuanMemberDTO = tuanMemberMapper.toDto(updatedTuanMember);

        restTuanMemberMockMvc.perform(put("/api/tuan-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanMemberDTO)))
            .andExpect(status().isOk());

        // Validate the TuanMember in the database
        List<TuanMember> tuanMemberList = tuanMemberRepository.findAll();
        assertThat(tuanMemberList).hasSize(databaseSizeBeforeUpdate);
        TuanMember testTuanMember = tuanMemberList.get(tuanMemberList.size() - 1);
        assertThat(testTuanMember.getTuanId()).isEqualTo(UPDATED_TUAN_ID);
        assertThat(testTuanMember.getMemberType()).isEqualTo(UPDATED_MEMBER_TYPE);
        assertThat(testTuanMember.getMemberDescrption()).isEqualTo(UPDATED_MEMBER_DESCRPTION);
        assertThat(testTuanMember.getYearsOld()).isEqualTo(UPDATED_YEARS_OLD);
        assertThat(testTuanMember.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testTuanMember.getFromCity()).isEqualTo(UPDATED_FROM_CITY);
        assertThat(testTuanMember.getJoinTime()).isEqualTo(UPDATED_JOIN_TIME);
        assertThat(testTuanMember.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTuanMember.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTuanMember.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testTuanMember.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTuanMember.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingTuanMember() throws Exception {
        int databaseSizeBeforeUpdate = tuanMemberRepository.findAll().size();

        // Create the TuanMember
        TuanMemberDTO tuanMemberDTO = tuanMemberMapper.toDto(tuanMember);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTuanMemberMockMvc.perform(put("/api/tuan-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuanMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the TuanMember in the database
        List<TuanMember> tuanMemberList = tuanMemberRepository.findAll();
        assertThat(tuanMemberList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTuanMember() throws Exception {
        // Initialize the database
        tuanMemberRepository.saveAndFlush(tuanMember);
        int databaseSizeBeforeDelete = tuanMemberRepository.findAll().size();

        // Get the tuanMember
        restTuanMemberMockMvc.perform(delete("/api/tuan-members/{id}", tuanMember.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TuanMember> tuanMemberList = tuanMemberRepository.findAll();
        assertThat(tuanMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TuanMember.class);
        TuanMember tuanMember1 = new TuanMember();
        tuanMember1.setId(1L);
        TuanMember tuanMember2 = new TuanMember();
        tuanMember2.setId(tuanMember1.getId());
        assertThat(tuanMember1).isEqualTo(tuanMember2);
        tuanMember2.setId(2L);
        assertThat(tuanMember1).isNotEqualTo(tuanMember2);
        tuanMember1.setId(null);
        assertThat(tuanMember1).isNotEqualTo(tuanMember2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TuanMemberDTO.class);
        TuanMemberDTO tuanMemberDTO1 = new TuanMemberDTO();
        tuanMemberDTO1.setId(1L);
        TuanMemberDTO tuanMemberDTO2 = new TuanMemberDTO();
        assertThat(tuanMemberDTO1).isNotEqualTo(tuanMemberDTO2);
        tuanMemberDTO2.setId(tuanMemberDTO1.getId());
        assertThat(tuanMemberDTO1).isEqualTo(tuanMemberDTO2);
        tuanMemberDTO2.setId(2L);
        assertThat(tuanMemberDTO1).isNotEqualTo(tuanMemberDTO2);
        tuanMemberDTO1.setId(null);
        assertThat(tuanMemberDTO1).isNotEqualTo(tuanMemberDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tuanMemberMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tuanMemberMapper.fromId(null)).isNull();
    }
}
