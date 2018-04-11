package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {TuanInfoMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "tuanInfo.id", target = "tuanInfoId")
    MessageDTO toDto(Message message);

    @Mapping(source = "tuanInfoId", target = "tuanInfo")
    @Mapping(target = "comments", ignore = true)
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
