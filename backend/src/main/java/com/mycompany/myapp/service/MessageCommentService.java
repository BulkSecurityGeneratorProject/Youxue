package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.MessageCommentDTO;
import java.util.List;

/**
 * Service Interface for managing MessageComment.
 */
public interface MessageCommentService {

    /**
     * Save a messageComment.
     *
     * @param messageCommentDTO the entity to save
     * @return the persisted entity
     */
    MessageCommentDTO save(MessageCommentDTO messageCommentDTO);

    /**
     * Get all the messageComments.
     *
     * @return the list of entities
     */
    List<MessageCommentDTO> findAll();

    /**
     * Get the "id" messageComment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MessageCommentDTO findOne(Long id);

    /**
     * Delete the "id" messageComment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
