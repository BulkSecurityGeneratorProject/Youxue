package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackendApp;

import com.mycompany.myapp.domain.Message;
import com.mycompany.myapp.repository.MessageRepository;
import com.mycompany.myapp.service.MessageService;
import com.mycompany.myapp.service.dto.MessageDTO;
import com.mycompany.myapp.service.mapper.MessageMapper;
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
 * Test class for the MessageResource REST controller.
 *
 * @see MessageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class MessageResourceIntTest {

    private static final Long DEFAULT_TUAN_ID = 1L;
    private static final Long UPDATED_TUAN_ID = 2L;

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final Long DEFAULT_DAY_ID = 1L;
    private static final Long UPDATED_DAY_ID = 2L;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_PIG_1 = "AAAAAAAAAA";
    private static final String UPDATED_PIG_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PIG_2 = "AAAAAAAAAA";
    private static final String UPDATED_PIG_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PIG_3 = "AAAAAAAAAA";
    private static final String UPDATED_PIG_3 = "BBBBBBBBBB";

    private static final String DEFAULT_VOICE = "AAAAAAAAAA";
    private static final String UPDATED_VOICE = "BBBBBBBBBB";

    private static final String DEFAULT_VUDIO = "AAAAAAAAAA";
    private static final String UPDATED_VUDIO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEPLOY_TO_QUAN = false;
    private static final Boolean UPDATED_DEPLOY_TO_QUAN = true;

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMessageMockMvc;

    private Message message;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MessageResource messageResource = new MessageResource(messageService);
        this.restMessageMockMvc = MockMvcBuilders.standaloneSetup(messageResource)
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
    public static Message createEntity(EntityManager em) {
        Message message = new Message()
            .tuanId(DEFAULT_TUAN_ID)
            .body(DEFAULT_BODY)
            .dayId(DEFAULT_DAY_ID)
            .type(DEFAULT_TYPE)
            .title(DEFAULT_TITLE)
            .pig1(DEFAULT_PIG_1)
            .pig2(DEFAULT_PIG_2)
            .pig3(DEFAULT_PIG_3)
            .voice(DEFAULT_VOICE)
            .vudio(DEFAULT_VUDIO)
            .deployToQuan(DEFAULT_DEPLOY_TO_QUAN)
            .creator(DEFAULT_CREATOR)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return message;
    }

    @Before
    public void initTest() {
        message = createEntity(em);
    }

    @Test
    @Transactional
    public void createMessage() throws Exception {
        int databaseSizeBeforeCreate = messageRepository.findAll().size();

        // Create the Message
        MessageDTO messageDTO = messageMapper.toDto(message);
        restMessageMockMvc.perform(post("/api/messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
            .andExpect(status().isCreated());

        // Validate the Message in the database
        List<Message> messageList = messageRepository.findAll();
        assertThat(messageList).hasSize(databaseSizeBeforeCreate + 1);
        Message testMessage = messageList.get(messageList.size() - 1);
        assertThat(testMessage.getTuanId()).isEqualTo(DEFAULT_TUAN_ID);
        assertThat(testMessage.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testMessage.getDayId()).isEqualTo(DEFAULT_DAY_ID);
        assertThat(testMessage.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMessage.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMessage.getPig1()).isEqualTo(DEFAULT_PIG_1);
        assertThat(testMessage.getPig2()).isEqualTo(DEFAULT_PIG_2);
        assertThat(testMessage.getPig3()).isEqualTo(DEFAULT_PIG_3);
        assertThat(testMessage.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testMessage.getVudio()).isEqualTo(DEFAULT_VUDIO);
        assertThat(testMessage.isDeployToQuan()).isEqualTo(DEFAULT_DEPLOY_TO_QUAN);
        assertThat(testMessage.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMessage.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testMessage.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messageRepository.findAll().size();

        // Create the Message with an existing ID
        message.setId(1L);
        MessageDTO messageDTO = messageMapper.toDto(message);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageMockMvc.perform(post("/api/messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Message in the database
        List<Message> messageList = messageRepository.findAll();
        assertThat(messageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBodyIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageRepository.findAll().size();
        // set the field null
        message.setBody(null);

        // Create the Message, which fails.
        MessageDTO messageDTO = messageMapper.toDto(message);

        restMessageMockMvc.perform(post("/api/messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
            .andExpect(status().isBadRequest());

        List<Message> messageList = messageRepository.findAll();
        assertThat(messageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMessages() throws Exception {
        // Initialize the database
        messageRepository.saveAndFlush(message);

        // Get all the messageList
        restMessageMockMvc.perform(get("/api/messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(message.getId().intValue())))
            .andExpect(jsonPath("$.[*].tuanId").value(hasItem(DEFAULT_TUAN_ID.intValue())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())))
            .andExpect(jsonPath("$.[*].dayId").value(hasItem(DEFAULT_DAY_ID.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].pig1").value(hasItem(DEFAULT_PIG_1.toString())))
            .andExpect(jsonPath("$.[*].pig2").value(hasItem(DEFAULT_PIG_2.toString())))
            .andExpect(jsonPath("$.[*].pig3").value(hasItem(DEFAULT_PIG_3.toString())))
            .andExpect(jsonPath("$.[*].voice").value(hasItem(DEFAULT_VOICE.toString())))
            .andExpect(jsonPath("$.[*].vudio").value(hasItem(DEFAULT_VUDIO.toString())))
            .andExpect(jsonPath("$.[*].deployToQuan").value(hasItem(DEFAULT_DEPLOY_TO_QUAN.booleanValue())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }

    @Test
    @Transactional
    public void getMessage() throws Exception {
        // Initialize the database
        messageRepository.saveAndFlush(message);

        // Get the message
        restMessageMockMvc.perform(get("/api/messages/{id}", message.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(message.getId().intValue()))
            .andExpect(jsonPath("$.tuanId").value(DEFAULT_TUAN_ID.intValue()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()))
            .andExpect(jsonPath("$.dayId").value(DEFAULT_DAY_ID.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.pig1").value(DEFAULT_PIG_1.toString()))
            .andExpect(jsonPath("$.pig2").value(DEFAULT_PIG_2.toString()))
            .andExpect(jsonPath("$.pig3").value(DEFAULT_PIG_3.toString()))
            .andExpect(jsonPath("$.voice").value(DEFAULT_VOICE.toString()))
            .andExpect(jsonPath("$.vudio").value(DEFAULT_VUDIO.toString()))
            .andExpect(jsonPath("$.deployToQuan").value(DEFAULT_DEPLOY_TO_QUAN.booleanValue()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMessage() throws Exception {
        // Get the message
        restMessageMockMvc.perform(get("/api/messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMessage() throws Exception {
        // Initialize the database
        messageRepository.saveAndFlush(message);
        int databaseSizeBeforeUpdate = messageRepository.findAll().size();

        // Update the message
        Message updatedMessage = messageRepository.findOne(message.getId());
        // Disconnect from session so that the updates on updatedMessage are not directly saved in db
        em.detach(updatedMessage);
        updatedMessage
            .tuanId(UPDATED_TUAN_ID)
            .body(UPDATED_BODY)
            .dayId(UPDATED_DAY_ID)
            .type(UPDATED_TYPE)
            .title(UPDATED_TITLE)
            .pig1(UPDATED_PIG_1)
            .pig2(UPDATED_PIG_2)
            .pig3(UPDATED_PIG_3)
            .voice(UPDATED_VOICE)
            .vudio(UPDATED_VUDIO)
            .deployToQuan(UPDATED_DEPLOY_TO_QUAN)
            .creator(UPDATED_CREATOR)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE);
        MessageDTO messageDTO = messageMapper.toDto(updatedMessage);

        restMessageMockMvc.perform(put("/api/messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
            .andExpect(status().isOk());

        // Validate the Message in the database
        List<Message> messageList = messageRepository.findAll();
        assertThat(messageList).hasSize(databaseSizeBeforeUpdate);
        Message testMessage = messageList.get(messageList.size() - 1);
        assertThat(testMessage.getTuanId()).isEqualTo(UPDATED_TUAN_ID);
        assertThat(testMessage.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testMessage.getDayId()).isEqualTo(UPDATED_DAY_ID);
        assertThat(testMessage.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMessage.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMessage.getPig1()).isEqualTo(UPDATED_PIG_1);
        assertThat(testMessage.getPig2()).isEqualTo(UPDATED_PIG_2);
        assertThat(testMessage.getPig3()).isEqualTo(UPDATED_PIG_3);
        assertThat(testMessage.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testMessage.getVudio()).isEqualTo(UPDATED_VUDIO);
        assertThat(testMessage.isDeployToQuan()).isEqualTo(UPDATED_DEPLOY_TO_QUAN);
        assertThat(testMessage.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMessage.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testMessage.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMessage() throws Exception {
        int databaseSizeBeforeUpdate = messageRepository.findAll().size();

        // Create the Message
        MessageDTO messageDTO = messageMapper.toDto(message);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMessageMockMvc.perform(put("/api/messages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageDTO)))
            .andExpect(status().isCreated());

        // Validate the Message in the database
        List<Message> messageList = messageRepository.findAll();
        assertThat(messageList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMessage() throws Exception {
        // Initialize the database
        messageRepository.saveAndFlush(message);
        int databaseSizeBeforeDelete = messageRepository.findAll().size();

        // Get the message
        restMessageMockMvc.perform(delete("/api/messages/{id}", message.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Message> messageList = messageRepository.findAll();
        assertThat(messageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Message.class);
        Message message1 = new Message();
        message1.setId(1L);
        Message message2 = new Message();
        message2.setId(message1.getId());
        assertThat(message1).isEqualTo(message2);
        message2.setId(2L);
        assertThat(message1).isNotEqualTo(message2);
        message1.setId(null);
        assertThat(message1).isNotEqualTo(message2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageDTO.class);
        MessageDTO messageDTO1 = new MessageDTO();
        messageDTO1.setId(1L);
        MessageDTO messageDTO2 = new MessageDTO();
        assertThat(messageDTO1).isNotEqualTo(messageDTO2);
        messageDTO2.setId(messageDTO1.getId());
        assertThat(messageDTO1).isEqualTo(messageDTO2);
        messageDTO2.setId(2L);
        assertThat(messageDTO1).isNotEqualTo(messageDTO2);
        messageDTO1.setId(null);
        assertThat(messageDTO1).isNotEqualTo(messageDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(messageMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(messageMapper.fromId(null)).isNull();
    }
}
