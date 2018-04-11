package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MessageCommentService;
import com.mycompany.myapp.domain.MessageComment;
import com.mycompany.myapp.repository.MessageCommentRepository;
import com.mycompany.myapp.service.dto.MessageCommentDTO;
import com.mycompany.myapp.service.mapper.MessageCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MessageComment.
 */
@Service
@Transactional
public class MessageCommentServiceImpl implements MessageCommentService {

    private final Logger log = LoggerFactory.getLogger(MessageCommentServiceImpl.class);

    private final MessageCommentRepository messageCommentRepository;

    private final MessageCommentMapper messageCommentMapper;

    public MessageCommentServiceImpl(MessageCommentRepository messageCommentRepository, MessageCommentMapper messageCommentMapper) {
        this.messageCommentRepository = messageCommentRepository;
        this.messageCommentMapper = messageCommentMapper;
    }

    /**
     * Save a messageComment.
     *
     * @param messageCommentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MessageCommentDTO save(MessageCommentDTO messageCommentDTO) {
        log.debug("Request to save MessageComment : {}", messageCommentDTO);
        MessageComment messageComment = messageCommentMapper.toEntity(messageCommentDTO);
        messageComment = messageCommentRepository.save(messageComment);
        return messageCommentMapper.toDto(messageComment);
    }

    /**
     * Get all the messageComments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MessageCommentDTO> findAll() {
        log.debug("Request to get all MessageComments");
        return messageCommentRepository.findAll().stream()
            .map(messageCommentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one messageComment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MessageCommentDTO findOne(Long id) {
        log.debug("Request to get MessageComment : {}", id);
        MessageComment messageComment = messageCommentRepository.findOne(id);
        return messageCommentMapper.toDto(messageComment);
    }

    /**
     * Delete the messageComment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MessageComment : {}", id);
        messageCommentRepository.delete(id);
    }
}
