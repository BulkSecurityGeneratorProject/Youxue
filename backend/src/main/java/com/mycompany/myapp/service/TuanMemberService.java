package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TuanMemberDTO;
import java.util.List;

/**
 * Service Interface for managing TuanMember.
 */
public interface TuanMemberService {

    /**
     * Save a tuanMember.
     *
     * @param tuanMemberDTO the entity to save
     * @return the persisted entity
     */
    TuanMemberDTO save(TuanMemberDTO tuanMemberDTO);

    /**
     * Get all the tuanMembers.
     *
     * @return the list of entities
     */
    List<TuanMemberDTO> findAll();

    /**
     * Get the "id" tuanMember.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TuanMemberDTO findOne(Long id);

    /**
     * Delete the "id" tuanMember.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
