package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DaySechudlerInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DaySechudlerInfo and its DTO DaySechudlerInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {TuanInfoMapper.class})
public interface DaySechudlerInfoMapper extends EntityMapper<DaySechudlerInfoDTO, DaySechudlerInfo> {

    @Mapping(source = "tuanInfo.id", target = "tuanInfoId")
    DaySechudlerInfoDTO toDto(DaySechudlerInfo daySechudlerInfo);

    @Mapping(source = "tuanInfoId", target = "tuanInfo")
    DaySechudlerInfo toEntity(DaySechudlerInfoDTO daySechudlerInfoDTO);

    default DaySechudlerInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        DaySechudlerInfo daySechudlerInfo = new DaySechudlerInfo();
        daySechudlerInfo.setId(id);
        return daySechudlerInfo;
    }
}
