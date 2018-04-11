package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.MessageCommentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.MessageCommentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MessageComment.
 */
@RestController
@RequestMapping("/api")
public class MessageCommentResource {

    private final Logger log = LoggerFactory.getLogger(MessageCommentResource.class);

    private static final String ENTITY_NAME = "messageComment";

    private final MessageCommentService messageCommentService;

    public MessageCommentResource(MessageCommentService messageCommentService) {
        this.messageCommentService = messageCommentService;
    }

    /**
     * POST  /message-comments : Create a new messageComment.
     *
     * @param messageCommentDTO the messageCommentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new messageCommentDTO, or with status 400 (Bad Request) if the messageComment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/message-comments")
    @Timed
    public ResponseEntity<MessageCommentDTO> createMessageComment(@Valid @RequestBody MessageCommentDTO messageCommentDTO) throws URISyntaxException {
        log.debug("REST request to save MessageComment : {}", messageCommentDTO);
        if (messageCommentDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessageCommentDTO result = messageCommentService.save(messageCommentDTO);
        return ResponseEntity.created(new URI("/api/message-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /message-comments : Updates an existing messageComment.
     *
     * @param messageCommentDTO the messageCommentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated messageCommentDTO,
     * or with status 400 (Bad Request) if the messageCommentDTO is not valid,
     * or with status 500 (Internal Server Error) if the messageCommentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/message-comments")
    @Timed
    public ResponseEntity<MessageCommentDTO> updateMessageComment(@Valid @RequestBody MessageCommentDTO messageCommentDTO) throws URISyntaxException {
        log.debug("REST request to update MessageComment : {}", messageCommentDTO);
        if (messageCommentDTO.getId() == null) {
            return createMessageComment(messageCommentDTO);
        }
        MessageCommentDTO result = messageCommentService.save(messageCommentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, messageCommentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /message-comments : get all the messageComments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of messageComments in body
     */
    @GetMapping("/message-comments")
    @Timed
    public List<MessageCommentDTO> getAllMessageComments() {
        log.debug("REST request to get all MessageComments");
        return messageCommentService.findAll();
        }

    /**
     * GET  /message-comments/:id : get the "id" messageComment.
     *
     * @param id the id of the messageCommentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the messageCommentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/message-comments/{id}")
    @Timed
    public ResponseEntity<MessageCommentDTO> getMessageComment(@PathVariable Long id) {
        log.debug("REST request to get MessageComment : {}", id);
        MessageCommentDTO messageCommentDTO = messageCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(messageCommentDTO));
    }

    /**
     * DELETE  /message-comments/:id : delete the "id" messageComment.
     *
     * @param id the id of the messageCommentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/message-comments/{id}")
    @Timed
    public ResponseEntity<Void> deleteMessageComment(@PathVariable Long id) {
        log.debug("REST request to delete MessageComment : {}", id);
        messageCommentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
