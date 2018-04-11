package com.mycompany.myapp.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the MessageComment entity.
 */
public class MessageCommentDTO implements Serializable {

    private Long id;

    private Long messageBelongId;

    private String creator;

    private Instant createDate;

    private Instant updateDate;

    @NotNull
    private String body;

    private Long messageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageBelongId() {
        return messageBelongId;
    }

    public void setMessageBelongId(Long messageBelongId) {
        this.messageBelongId = messageBelongId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageCommentDTO messageCommentDTO = (MessageCommentDTO) o;
        if(messageCommentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageCommentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageCommentDTO{" +
            "id=" + getId() +
            ", messageBelongId=" + getMessageBelongId() +
            ", creator='" + getCreator() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
}
