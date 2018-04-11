package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TuanInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TuanInfo and its DTO TuanInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TuanInfoMapper extends EntityMapper<TuanInfoDTO, TuanInfo> {


    @Mapping(target = "tuanmembers", ignore = true)
    @Mapping(target = "daysechudlerinfos", ignore = true)
    @Mapping(target = "messages", ignore = true)
    TuanInfo toEntity(TuanInfoDTO tuanInfoDTO);

    default TuanInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        TuanInfo tuanInfo = new TuanInfo();
        tuanInfo.setId(id);
        return tuanInfo;
    }
}
