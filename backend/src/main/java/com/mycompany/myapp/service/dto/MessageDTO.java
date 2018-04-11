package com.mycompany.myapp.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Message entity.
 */
public class MessageDTO implements Serializable {

    private Long id;

    private Long tuanId;

    @NotNull
    private String body;

    private Long dayId;

    private String type;

    private String title;

    private String pig1;

    private String pig2;

    private String pig3;

    private String voice;

    private String vudio;

    private Boolean deployToQuan;

    private String creator;

    private Instant createDate;

    private Instant updateDate;

    private Long tuanInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTuanId() {
        return tuanId;
    }

    public void setTuanId(Long tuanId) {
        this.tuanId = tuanId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getDayId() {
        return dayId;
    }

    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPig1() {
        return pig1;
    }

    public void setPig1(String pig1) {
        this.pig1 = pig1;
    }

    public String getPig2() {
        return pig2;
    }

    public void setPig2(String pig2) {
        this.pig2 = pig2;
    }

    public String getPig3() {
        return pig3;
    }

    public void setPig3(String pig3) {
        this.pig3 = pig3;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getVudio() {
        return vudio;
    }

    public void setVudio(String vudio) {
        this.vudio = vudio;
    }

    public Boolean isDeployToQuan() {
        return deployToQuan;
    }

    public void setDeployToQuan(Boolean deployToQuan) {
        this.deployToQuan = deployToQuan;
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

    public Long getTuanInfoId() {
        return tuanInfoId;
    }

    public void setTuanInfoId(Long tuanInfoId) {
        this.tuanInfoId = tuanInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDTO messageDTO = (MessageDTO) o;
        if(messageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
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
