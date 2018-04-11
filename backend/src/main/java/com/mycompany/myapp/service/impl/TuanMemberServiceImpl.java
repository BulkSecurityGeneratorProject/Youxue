package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TuanMemberService;
import com.mycompany.myapp.domain.TuanMember;
import com.mycompany.myapp.repository.TuanMemberRepository;
import com.mycompany.myapp.service.dto.TuanMemberDTO;
import com.mycompany.myapp.service.mapper.TuanMemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TuanMember.
 */
@Service
@Transactional
public class TuanMemberServiceImpl implements TuanMemberService {

    private final Logger log = LoggerFactory.getLogger(TuanMemberServiceImpl.class);

    private final TuanMemberRepository tuanMemberRepository;

    private final TuanMemberMapper tuanMemberMapper;

    public TuanMemberServiceImpl(TuanMemberRepository tuanMemberRepository, TuanMemberMapper tuanMemberMapper) {
        this.tuanMemberRepository = tuanMemberRepository;
        this.tuanMemberMapper = tuanMemberMapper;
    }

    /**
     * Save a tuanMember.
     *
     * @param tuanMemberDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TuanMemberDTO save(TuanMemberDTO tuanMemberDTO) {
        log.debug("Request to save TuanMember : {}", tuanMemberDTO);
        TuanMember tuanMember = tuanMemberMapper.toEntity(tuanMemberDTO);
        tuanMember = tuanMemberRepository.save(tuanMember);
        return tuanMemberMapper.toDto(tuanMember);
    }

    /**
     * Get all the tuanMembers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TuanMemberDTO> findAll() {
        log.debug("Request to get all TuanMembers");
        return tuanMemberRepository.findAll().stream()
            .map(tuanMemberMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tuanMember by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TuanMemberDTO findOne(Long id) {
        log.debug("Request to get TuanMember : {}", id);
        TuanMember tuanMember = tuanMemberRepository.findOne(id);
        return tuanMemberMapper.toDto(tuanMember);
    }

    /**
     * Delete the tuanMember by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TuanMember : {}", id);
        tuanMemberRepository.delete(id);
    }
}
