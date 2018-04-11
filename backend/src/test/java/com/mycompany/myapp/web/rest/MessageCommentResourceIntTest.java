package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.BackendApp;

import com.mycompany.myapp.domain.MessageComment;
import com.mycompany.myapp.repository.MessageCommentRepository;
import com.mycompany.myapp.service.MessageCommentService;
import com.mycompany.myapp.service.dto.MessageCommentDTO;
import com.mycompany.myapp.service.mapper.MessageCommentMapper;
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
 * Test class for the MessageCommentResource REST controller.
 *
 * @see MessageCommentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApp.class)
public class MessageCommentResourceIntTest {

    private static final Long DEFAULT_MESSAGE_BELONG_ID = 1L;
    private static final Long UPDATED_MESSAGE_BELONG_ID = 2L;

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    @Autowired
    private MessageCommentRepository messageCommentRepository;

    @Autowired
    private MessageCommentMapper messageCommentMapper;

    @Autowired
    private MessageCommentService messageCommentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMessageCommentMockMvc;

    private MessageComment messageComment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MessageCommentResource messageCommentResource = new MessageCommentResource(messageCommentService);
        this.restMessageCommentMockMvc = MockMvcBuilders.standaloneSetup(messageCommentResource)
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
    public static MessageComment createEntity(EntityManager em) {
        MessageComment messageComment = new MessageComment()
            .messageBelongId(DEFAULT_MESSAGE_BELONG_ID)
            .creator(DEFAULT_CREATOR)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .body(DEFAULT_BODY);
        return messageComment;
    }

    @Before
    public void initTest() {
        messageComment = createEntity(em);
    }

    @Test
    @Transactional
    public void createMessageComment() throws Exception {
        int databaseSizeBeforeCreate = messageCommentRepository.findAll().size();

        // Create the MessageComment
        MessageCommentDTO messageCommentDTO = messageCommentMapper.toDto(messageComment);
        restMessageCommentMockMvc.perform(post("/api/message-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageCommentDTO)))
            .andExpect(status().isCreated());

        // Validate the MessageComment in the database
        List<MessageComment> messageCommentList = messageCommentRepository.findAll();
        assertThat(messageCommentList).hasSize(databaseSizeBeforeCreate + 1);
        MessageComment testMessageComment = messageCommentList.get(messageCommentList.size() - 1);
        assertThat(testMessageComment.getMessageBelongId()).isEqualTo(DEFAULT_MESSAGE_BELONG_ID);
        assertThat(testMessageComment.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMessageComment.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testMessageComment.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testMessageComment.getBody()).isEqualTo(DEFAULT_BODY);
    }

    @Test
    @Transactional
    public void createMessageCommentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messageCommentRepository.findAll().size();

        // Create the MessageComment with an existing ID
        messageComment.setId(1L);
        MessageCommentDTO messageCommentDTO = messageCommentMapper.toDto(messageComment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageCommentMockMvc.perform(post("/api/message-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageCommentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageComment in the database
        List<MessageComment> messageCommentList = messageCommentRepository.findAll();
        assertThat(messageCommentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBodyIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageCommentRepository.findAll().size();
        // set the field null
        messageComment.setBody(null);

        // Create the MessageComment, which fails.
        MessageCommentDTO messageCommentDTO = messageCommentMapper.toDto(messageComment);

        restMessageCommentMockMvc.perform(post("/api/message-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageCommentDTO)))
            .andExpect(status().isBadRequest());

        List<MessageComment> messageCommentList = messageCommentRepository.findAll();
        assertThat(messageCommentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMessageComments() throws Exception {
        // Initialize the database
        messageCommentRepository.saveAndFlush(messageComment);

        // Get all the messageCommentList
        restMessageCommentMockMvc.perform(get("/api/message-comments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageComment.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageBelongId").value(hasItem(DEFAULT_MESSAGE_BELONG_ID.intValue())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY.toString())));
    }

    @Test
    @Transactional
    public void getMessageComment() throws Exception {
        // Initialize the database
        messageCommentRepository.saveAndFlush(messageComment);

        // Get the messageComment
        restMessageCommentMockMvc.perform(get("/api/message-comments/{id}", messageComment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(messageComment.getId().intValue()))
            .andExpect(jsonPath("$.messageBelongId").value(DEFAULT_MESSAGE_BELONG_ID.intValue()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMessageComment() throws Exception {
        // Get the messageComment
        restMessageCommentMockMvc.perform(get("/api/message-comments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMessageComment() throws Exception {
        // Initialize the database
        messageCommentRepository.saveAndFlush(messageComment);
        int databaseSizeBeforeUpdate = messageCommentRepository.findAll().size();

        // Update the messageComment
        MessageComment updatedMessageComment = messageCommentRepository.findOne(messageComment.getId());
        // Disconnect from session so that the updates on updatedMessageComment are not directly saved in db
        em.detach(updatedMessageComment);
        updatedMessageComment
            .messageBelongId(UPDATED_MESSAGE_BELONG_ID)
            .creator(UPDATED_CREATOR)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .body(UPDATED_BODY);
        MessageCommentDTO messageCommentDTO = messageCommentMapper.toDto(updatedMessageComment);

        restMessageCommentMockMvc.perform(put("/api/message-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageCommentDTO)))
            .andExpect(status().isOk());

        // Validate the MessageComment in the database
        List<MessageComment> messageCommentList = messageCommentRepository.findAll();
        assertThat(messageCommentList).hasSize(databaseSizeBeforeUpdate);
        MessageComment testMessageComment = messageCommentList.get(messageCommentList.size() - 1);
        assertThat(testMessageComment.getMessageBelongId()).isEqualTo(UPDATED_MESSAGE_BELONG_ID);
        assertThat(testMessageComment.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMessageComment.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testMessageComment.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testMessageComment.getBody()).isEqualTo(UPDATED_BODY);
    }

    @Test
    @Transactional
    public void updateNonExistingMessageComment() throws Exception {
        int databaseSizeBeforeUpdate = messageCommentRepository.findAll().size();

        // Create the MessageComment
        MessageCommentDTO messageCommentDTO = messageCommentMapper.toDto(messageComment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMessageCommentMockMvc.perform(put("/api/message-comments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageCommentDTO)))
            .andExpect(status().isCreated());

        // Validate the MessageComment in the database
        List<MessageComment> messageCommentList = messageCommentRepository.findAll();
        assertThat(messageCommentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMessageComment() throws Exception {
        // Initialize the database
        messageCommentRepository.saveAndFlush(messageComment);
        int databaseSizeBeforeDelete = messageCommentRepository.findAll().size();

        // Get the messageComment
        restMessageCommentMockMvc.perform(delete("/api/message-comments/{id}", messageComment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MessageComment> messageCommentList = messageCommentRepository.findAll();
        assertThat(messageCommentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageComment.class);
        MessageComment messageComment1 = new MessageComment();
        messageComment1.setId(1L);
        MessageComment messageComment2 = new MessageComment();
        messageComment2.setId(messageComment1.getId());
        assertThat(messageComment1).isEqualTo(messageComment2);
        messageComment2.setId(2L);
        assertThat(messageComment1).isNotEqualTo(messageComment2);
        messageComment1.setId(null);
        assertThat(messageComment1).isNotEqualTo(messageComment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageCommentDTO.class);
        MessageCommentDTO messageCommentDTO1 = new MessageCommentDTO();
        messageCommentDTO1.setId(1L);
        MessageCommentDTO messageCommentDTO2 = new MessageCommentDTO();
        assertThat(messageCommentDTO1).isNotEqualTo(messageCommentDTO2);
        messageCommentDTO2.setId(messageCommentDTO1.getId());
        assertThat(messageCommentDTO1).isEqualTo(messageCommentDTO2);
        messageCommentDTO2.setId(2L);
        assertThat(messageCommentDTO1).isNotEqualTo(messageCommentDTO2);
        messageCommentDTO1.setId(null);
        assertThat(messageCommentDTO1).isNotEqualTo(messageCommentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(messageCommentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(messageCommentMapper.fromId(null)).isNull();
    }
}
