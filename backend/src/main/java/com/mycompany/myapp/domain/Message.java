package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tuan_id")
    private Long tuanId;

    @NotNull
    @Column(name = "jhi_body", nullable = false)
    private String body;

    @Column(name = "day_id")
    private Long dayId;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "pig_1")
    private String pig1;

    @Column(name = "pig_2")
    private String pig2;

    @Column(name = "pig_3")
    private String pig3;

    @Column(name = "voice")
    private String voice;

    @Column(name = "vudio")
    private String vudio;

    @Column(name = "deploy_to_quan")
    private Boolean deployToQuan;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @ManyToOne
    private TuanInfo tuanInfo;

    @OneToMany(mappedBy = "message")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MessageComment> comments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTuanId() {
        return tuanId;
    }

    public Message tuanId(Long tuanId) {
        this.tuanId = tuanId;
        return this;
    }

    public void setTuanId(Long tuanId) {
        this.tuanId = tuanId;
    }

    public String getBody() {
        return body;
    }

    public Message body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getDayId() {
        return dayId;
    }

    public Message dayId(Long dayId) {
        this.dayId = dayId;
        return this;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public String getType() {
        return type;
    }

    public Message type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Message title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPig1() {
        return pig1;
    }

    public Message pig1(String pig1) {
        this.pig1 = pig1;
        return this;
    }

    public void setPig1(String pig1) {
        this.pig1 = pig1;
    }

    public String getPig2() {
        return pig2;
    }

    public Message pig2(String pig2) {
        this.pig2 = pig2;
        return this;
    }

    public void setPig2(String pig2) {
        this.pig2 = pig2;
    }

    public String getPig3() {
        return pig3;
    }

    public Message pig3(String pig3) {
        this.pig3 = pig3;
        return this;
    }

    public void setPig3(String pig3) {
        this.pig3 = pig3;
    }

    public String getVoice() {
        return voice;
    }

    public Message voice(String voice) {
        this.voice = voice;
        return this;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getVudio() {
        return vudio;
    }

    public Message vudio(String vudio) {
        this.vudio = vudio;
        return this;
    }

    public void setVudio(String vudio) {
        this.vudio = vudio;
    }

    public Boolean isDeployToQuan() {
        return deployToQuan;
    }

    public Message deployToQuan(Boolean deployToQuan) {
        this.deployToQuan = deployToQuan;
        return this;
    }

    public void setDeployToQuan(Boolean deployToQuan) {
        this.deployToQuan = deployToQuan;
    }

    public String getCreator() {
        return creator;
    }

    public Message creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Message createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public Message updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public TuanInfo getTuanInfo() {
        return tuanInfo;
    }

    public Message tuanInfo(TuanInfo tuanInfo) {
        this.tuanInfo = tuanInfo;
        return this;
    }

    public void setTuanInfo(TuanInfo tuanInfo) {
        this.tuanInfo = tuanInfo;
    }

    public Set<MessageComment> getComments() {
        return comments;
    }

    public Message comments(Set<MessageComment> messageComments) {
        this.comments = messageComments;
        return this;
    }

    public Message addComment(MessageComment messageComment) {
        this.comments.add(messageComment);
        messageComment.setMessage(this);
        return this;
    }

    public Message removeComment(MessageComment messageComment) {
        this.comments.remove(messageComment);
        messageComment.setMessage(null);
        return this;
    }

    public void setComments(Set<MessageComment> messageComments) {
        this.comments = messageComments;
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
        Message message = (Message) o;
        if (message.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), message.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", tuanId=" + getTuanId() +
            ", body='" + getBody() + "'" +
            ", dayId=" + getDayId() +
            ", type='" + getType() + "'" +
            ", title='" + getTitle() + "'" +
            ", pig1='" + getPig1() + "'" +
            ", pig2='" + getPig2() + "'" +
            ", pig3='" + getPig3() + "'" +
            ", voice='" + getVoice() + "'" +
            ", vudio='" + getVudio() + "'" +
            ", deployToQuan='" + isDeployToQuan() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
