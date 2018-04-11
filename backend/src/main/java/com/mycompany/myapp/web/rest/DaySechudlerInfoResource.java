package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.DaySechudlerInfoService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.DaySechudlerInfoDTO;
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
 * REST controller for managing DaySechudlerInfo.
 */
@RestController
@RequestMapping("/api")
public class DaySechudlerInfoResource {

    private final Logger log = LoggerFactory.getLogger(DaySechudlerInfoResource.class);

    private static final String ENTITY_NAME = "daySechudlerInfo";

    private final DaySechudlerInfoService daySechudlerInfoService;

    public DaySechudlerInfoResource(DaySechudlerInfoService daySechudlerInfoService) {
        this.daySechudlerInfoService = daySechudlerInfoService;
    }

    /**
     * POST  /day-sechudler-infos : Create a new daySechudlerInfo.
     *
     * @param daySechudlerInfoDTO the daySechudlerInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new daySechudlerInfoDTO, or with status 400 (Bad Request) if the daySechudlerInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/day-sechudler-infos")
    @Timed
    public ResponseEntity<DaySechudlerInfoDTO> createDaySechudlerInfo(@RequestBody DaySechudlerInfoDTO daySechudlerInfoDTO) throws URISyntaxException {
        log.debug("REST request to save DaySechudlerInfo : {}", daySechudlerInfoDTO);
        if (daySechudlerInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new daySechudlerInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DaySechudlerInfoDTO result = daySechudlerInfoService.save(daySechudlerInfoDTO);
        return ResponseEntity.created(new URI("/api/day-sechudler-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /day-sechudler-infos : Updates an existing daySechudlerInfo.
     *
     * @param daySechudlerInfoDTO the daySechudlerInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated daySechudlerInfoDTO,
     * or with status 400 (Bad Request) if the daySechudlerInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the daySechudlerInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/day-sechudler-infos")
    @Timed
    public ResponseEntity<DaySechudlerInfoDTO> updateDaySechudlerInfo(@RequestBody DaySechudlerInfoDTO daySechudlerInfoDTO) throws URISyntaxException {
        log.debug("REST request to update DaySechudlerInfo : {}", daySechudlerInfoDTO);
        if (daySechudlerInfoDTO.getId() == null) {
            return createDaySechudlerInfo(daySechudlerInfoDTO);
        }
        DaySechudlerInfoDTO result = daySechudlerInfoService.save(daySechudlerInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, daySechudlerInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /day-sechudler-infos : get all the daySechudlerInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of daySechudlerInfos in body
     */
    @GetMapping("/day-sechudler-infos")
    @Timed
    public List<DaySechudlerInfoDTO> getAllDaySechudlerInfos() {
        log.debug("REST request to get all DaySechudlerInfos");
        return daySechudlerInfoService.findAll();
        }

    /**
     * GET  /day-sechudler-infos/:id : get the "id" daySechudlerInfo.
     *
     * @param id the id of the daySechudlerInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the daySechudlerInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/day-sechudler-infos/{id}")
    @Timed
    public ResponseEntity<DaySechudlerInfoDTO> getDaySechudlerInfo(@PathVariable Long id) {
        log.debug("REST request to get DaySechudlerInfo : {}", id);
        DaySechudlerInfoDTO daySechudlerInfoDTO = daySechudlerInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(daySechudlerInfoDTO));
    }

    /**
     * DELETE  /day-sechudler-infos/:id : delete the "id" daySechudlerInfo.
     *
     * @param id the id of the daySechudlerInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/day-sechudler-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDaySechudlerInfo(@PathVariable Long id) {
        log.debug("REST request to delete DaySechudlerInfo : {}", id);
        daySechudlerInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
