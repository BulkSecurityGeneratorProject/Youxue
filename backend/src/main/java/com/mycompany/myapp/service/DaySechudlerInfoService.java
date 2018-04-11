package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DaySechudlerInfoDTO;
import java.util.List;

/**
 * Service Interface for managing DaySechudlerInfo.
 */
public interface DaySechudlerInfoService {

    /**
     * Save a daySechudlerInfo.
     *
     * @param daySechudlerInfoDTO the entity to save
     * @return the persisted entity
     */
    DaySechudlerInfoDTO save(DaySechudlerInfoDTO daySechudlerInfoDTO);

    /**
     * Get all the daySechudlerInfos.
     *
     * @return the list of entities
     */
    List<DaySechudlerInfoDTO> findAll();

    /**
     * Get the "id" daySechudlerInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DaySechudlerInfoDTO findOne(Long id);

    /**
     * Delete the "id" daySechudlerInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
