package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TuanMemberDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TuanMember and its DTO TuanMemberDTO.
 */
@Mapper(componentModel = "spring", uses = {TuanInfoMapper.class})
public interface TuanMemberMapper extends EntityMapper<TuanMemberDTO, TuanMember> {

    @Mapping(source = "tuanInfo.id", target = "tuanInfoId")
    TuanMemberDTO toDto(TuanMember tuanMember);

    @Mapping(source = "tuanInfoId", target = "tuanInfo")
    TuanMember toEntity(TuanMemberDTO tuanMemberDTO);

    default TuanMember fromId(Long id) {
        if (id == null) {
            return null;
        }
        TuanMember tuanMember = new TuanMember();
        tuanMember.setId(id);
        return tuanMember;
    }
}
