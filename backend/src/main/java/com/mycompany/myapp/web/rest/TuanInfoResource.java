package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.TuanInfoService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.TuanInfoDTO;
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
 * REST controller for managing TuanInfo.
 */
@RestController
@RequestMapping("/api")
public class TuanInfoResource {

    private final Logger log = LoggerFactory.getLogger(TuanInfoResource.class);

    private static final String ENTITY_NAME = "tuanInfo";

    private final TuanInfoService tuanInfoService;

    public TuanInfoResource(TuanInfoService tuanInfoService) {
        this.tuanInfoService = tuanInfoService;
    }

    /**
     * POST  /tuan-infos : Create a new tuanInfo.
     *
     * @param tuanInfoDTO the tuanInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tuanInfoDTO, or with status 400 (Bad Request) if the tuanInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tuan-infos")
    @Timed
    public ResponseEntity<TuanInfoDTO> createTuanInfo(@RequestBody TuanInfoDTO tuanInfoDTO) throws URISyntaxException {
        log.debug("REST request to save TuanInfo : {}", tuanInfoDTO);
        if (tuanInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tuanInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TuanInfoDTO result = tuanInfoService.save(tuanInfoDTO);
        return ResponseEntity.created(new URI("/api/tuan-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tuan-infos : Updates an existing tuanInfo.
     *
     * @param tuanInfoDTO the tuanInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tuanInfoDTO,
     * or with status 400 (Bad Request) if the tuanInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tuanInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tuan-infos")
    @Timed
    public ResponseEntity<TuanInfoDTO> updateTuanInfo(@RequestBody TuanInfoDTO tuanInfoDTO) throws URISyntaxException {
        log.debug("REST request to update TuanInfo : {}", tuanInfoDTO);
        if (tuanInfoDTO.getId() == null) {
            return createTuanInfo(tuanInfoDTO);
        }
        TuanInfoDTO result = tuanInfoService.save(tuanInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tuanInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tuan-infos : get all the tuanInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tuanInfos in body
     */
    @GetMapping("/tuan-infos")
    @Timed
    public List<TuanInfoDTO> getAllTuanInfos() {
        log.debug("REST request to get all TuanInfos");
        return tuanInfoService.findAll();
        }

    /**
     * GET  /tuan-infos/:id : get the "id" tuanInfo.
     *
     * @param id the id of the tuanInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tuanInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tuan-infos/{id}")
    @Timed
    public ResponseEntity<TuanInfoDTO> getTuanInfo(@PathVariable Long id) {
        log.debug("REST request to get TuanInfo : {}", id);
        TuanInfoDTO tuanInfoDTO = tuanInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tuanInfoDTO));
    }

    /**
     * DELETE  /tuan-infos/:id : delete the "id" tuanInfo.
     *
     * @param id the id of the tuanInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tuan-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTuanInfo(@PathVariable Long id) {
        log.debug("REST request to delete TuanInfo : {}", id);
        tuanInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
