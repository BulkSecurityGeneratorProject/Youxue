package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DaySechudlerInfoService;
import com.mycompany.myapp.domain.DaySechudlerInfo;
import com.mycompany.myapp.repository.DaySechudlerInfoRepository;
import com.mycompany.myapp.service.dto.DaySechudlerInfoDTO;
import com.mycompany.myapp.service.mapper.DaySechudlerInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DaySechudlerInfo.
 */
@Service
@Transactional
public class DaySechudlerInfoServiceImpl implements DaySechudlerInfoService {

    private final Logger log = LoggerFactory.getLogger(DaySechudlerInfoServiceImpl.class);

    private final DaySechudlerInfoRepository daySechudlerInfoRepository;

    private final DaySechudlerInfoMapper daySechudlerInfoMapper;

    public DaySechudlerInfoServiceImpl(DaySechudlerInfoRepository daySechudlerInfoRepository, DaySechudlerInfoMapper daySechudlerInfoMapper) {
        this.daySechudlerInfoRepository = daySechudlerInfoRepository;
        this.daySechudlerInfoMapper = daySechudlerInfoMapper;
    }

    /**
     * Save a daySechudlerInfo.
     *
     * @param daySechudlerInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DaySechudlerInfoDTO save(DaySechudlerInfoDTO daySechudlerInfoDTO) {
        log.debug("Request to save DaySechudlerInfo : {}", daySechudlerInfoDTO);
        DaySechudlerInfo daySechudlerInfo = daySechudlerInfoMapper.toEntity(daySechudlerInfoDTO);
        daySechudlerInfo = daySechudlerInfoRepository.save(daySechudlerInfo);
        return daySechudlerInfoMapper.toDto(daySechudlerInfo);
    }

    /**
     * Get all the daySechudlerInfos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DaySechudlerInfoDTO> findAll() {
        log.debug("Request to get all DaySechudlerInfos");
        return daySechudlerInfoRepository.findAll().stream()
            .map(daySechudlerInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one daySechudlerInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DaySechudlerInfoDTO findOne(Long id) {
        log.debug("Request to get DaySechudlerInfo : {}", id);
        DaySechudlerInfo daySechudlerInfo = daySechudlerInfoRepository.findOne(id);
        return daySechudlerInfoMapper.toDto(daySechudlerInfo);
    }

    /**
     * Delete the daySechudlerInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DaySechudlerInfo : {}", id);
        daySechudlerInfoRepository.delete(id);
    }
}
