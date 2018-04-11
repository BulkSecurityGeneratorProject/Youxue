package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TuanInfoService;
import com.mycompany.myapp.domain.TuanInfo;
import com.mycompany.myapp.repository.TuanInfoRepository;
import com.mycompany.myapp.service.dto.TuanInfoDTO;
import com.mycompany.myapp.service.mapper.TuanInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TuanInfo.
 */
@Service
@Transactional
public class TuanInfoServiceImpl implements TuanInfoService {

    private final Logger log = LoggerFactory.getLogger(TuanInfoServiceImpl.class);

    private final TuanInfoRepository tuanInfoRepository;

    private final TuanInfoMapper tuanInfoMapper;

    public TuanInfoServiceImpl(TuanInfoRepository tuanInfoRepository, TuanInfoMapper tuanInfoMapper) {
        this.tuanInfoRepository = tuanInfoRepository;
        this.tuanInfoMapper = tuanInfoMapper;
    }

    /**
     * Save a tuanInfo.
     *
     * @param tuanInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TuanInfoDTO save(TuanInfoDTO tuanInfoDTO) {
        log.debug("Request to save TuanInfo : {}", tuanInfoDTO);
        TuanInfo tuanInfo = tuanInfoMapper.toEntity(tuanInfoDTO);
        tuanInfo = tuanInfoRepository.save(tuanInfo);
        return tuanInfoMapper.toDto(tuanInfo);
    }

    /**
     * Get all the tuanInfos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TuanInfoDTO> findAll() {
        log.debug("Request to get all TuanInfos");
        return tuanInfoRepository.findAll().stream()
            .map(tuanInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tuanInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TuanInfoDTO findOne(Long id) {
        log.debug("Request to get TuanInfo : {}", id);
        TuanInfo tuanInfo = tuanInfoRepository.findOne(id);
        return tuanInfoMapper.toDto(tuanInfo);
    }

    /**
     * Delete the tuanInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TuanInfo : {}", id);
        tuanInfoRepository.delete(id);
    }
}
