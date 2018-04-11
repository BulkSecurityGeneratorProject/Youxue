package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.TuanMemberService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.TuanMemberDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TuanMember.
 */
@RestController
@RequestMapping("/api")
public class TuanMemberResource {

    private final Logger log = LoggerFactory.getLogger(TuanMemberResource.class);

    private static final String ENTITY_NAME = "tuanMember";

    private final TuanMemberService tuanMemberService;

    public TuanMemberResource(TuanMemberService tuanMemberService) {
        this.tuanMemberService = tuanMemberService;
    }

    /**
     * POST  /tuan-members : Create a new tuanMember.
     *
     * @param tuanMemberDTO the tuanMemberDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tuanMemberDTO, or with status 400 (Bad Request) if the tuanMember has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tuan-members")
    @Timed
    public ResponseEntity<TuanMemberDTO> createTuanMember(@RequestBody TuanMemberDTO tuanMemberDTO) throws URISyntaxException {
        log.debug("REST request to save TuanMember : {}", tuanMemberDTO);
        if (tuanMemberDTO.getId() != null) {
            throw new BadRequestAlertException("A new tuanMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TuanMemberDTO result = tuanMemberService.save(tuanMemberDTO);
        return ResponseEntity.created(new URI("/api/tuan-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tuan-members : Updates an existing tuanMember.
     *
     * @param tuanMemberDTO the tuanMemberDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tuanMemberDTO,
     * or with status 400 (Bad Request) if the tuanMemberDTO is not valid,
     * or with status 500 (Internal Server Error) if the tuanMemberDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tuan-members")
    @Timed
    public ResponseEntity<TuanMemberDTO> updateTuanMember(@RequestBody TuanMemberDTO tuanMemberDTO) throws URISyntaxException {
        log.debug("REST request to update TuanMember : {}", tuanMemberDTO);
        if (tuanMemberDTO.getId() == null) {
            return createTuanMember(tuanMemberDTO);
        }
        TuanMemberDTO result = tuanMemberService.save(tuanMemberDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tuanMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tuan-members : get all the tuanMembers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tuanMembers in body
     */
    @GetMapping("/tuan-members")
    @Timed
    public List<TuanMemberDTO> getAllTuanMembers() {
        log.debug("REST request to get all TuanMembers");
        return tuanMemberService.findAll();
        }

    /**
     * GET  /tuan-members/:id : get the "id" tuanMember.
     *
     * @param id the id of the tuanMemberDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tuanMemberDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tuan-members/{id}")
    @Timed
    public ResponseEntity<TuanMemberDTO> getTuanMember(@PathVariable Long id) {
        log.debug("REST request to get TuanMember : {}", id);
        TuanMemberDTO tuanMemberDTO = tuanMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tuanMemberDTO));
    }

    /**
     * DELETE  /tuan-members/:id : delete the "id" tuanMember.
     *
     * @param id the id of the tuanMemberDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tuan-members/{id}")
    @Timed
    public ResponseEntity<Void> deleteTuanMember(@PathVariable Long id) {
        log.debug("REST request to delete TuanMember : {}", id);
        tuanMemberService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
