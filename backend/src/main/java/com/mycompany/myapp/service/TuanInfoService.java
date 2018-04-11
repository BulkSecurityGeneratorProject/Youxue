package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TuanInfoDTO;
import java.util.List;

/**
 * Service Interface for managing TuanInfo.
 */
public interface TuanInfoService {

    /**
     * Save a tuanInfo.
     *
     * @param tuanInfoDTO the entity to save
     * @return the persisted entity
     */
    TuanInfoDTO save(TuanInfoDTO tuanInfoDTO);

    /**
     * Get all the tuanInfos.
     *
     * @return the list of entities
     */
    List<TuanInfoDTO> findAll();

    /**
     * Get the "id" tuanInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TuanInfoDTO findOne(Long id);

    /**
     * Delete the "id" tuanInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
