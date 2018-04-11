package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MessageCommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MessageComment and its DTO MessageCommentDTO.
 */
@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public interface MessageCommentMapper extends EntityMapper<MessageCommentDTO, MessageComment> {

    @Mapping(source = "message.id", target = "messageId")
    MessageCommentDTO toDto(MessageComment messageComment);

    @Mapping(source = "messageId", target = "message")
    MessageComment toEntity(MessageCommentDTO messageCommentDTO);

    default MessageComment fromId(Long id) {
        if (id == null) {
            return null;
        }
        MessageComment messageComment = new MessageComment();
        messageComment.setId(id);
        return messageComment;
    }
}
