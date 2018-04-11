package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Task entity.
 * @author The JHipster team.
 */
@ApiModel(description = "Task entity. @author The JHipster team.")
@Entity
@Table(name = "message_comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MessageComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_belong_id")
    private Long messageBelongId;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @NotNull
    @Column(name = "jhi_body", nullable = false)
    private String body;

    @ManyToOne
    private Message message;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageBelongId() {
        return messageBelongId;
    }

    public MessageComment messageBelongId(Long messageBelongId) {
        this.messageBelongId = messageBelongId;
        return this;
    }

    public void setMessageBelongId(Long messageBelongId) {
        this.messageBelongId = messageBelongId;
    }

    public String getCreator() {
        return creator;
    }

    public MessageComment creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public MessageComment createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public MessageComment updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getBody() {
        return body;
    }

    public MessageComment body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message getMessage() {
        return message;
    }

    public MessageComment message(Message message) {
        this.message = message;
        return this;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageComment messageComment = (MessageComment) o;
        if (messageComment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageComment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageComment{" +
            "id=" + getId() +
            ", messageBelongId=" + getMessageBelongId() +
            ", creator='" + getCreator() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
}
