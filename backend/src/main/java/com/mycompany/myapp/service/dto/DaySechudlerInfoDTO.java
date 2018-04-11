package com.mycompany.myapp.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DaySechudlerInfo entity.
 */
public class DaySechudlerInfoDTO implements Serializable {

    private Long id;

    private Long tuanId;

    private Instant time;

    private String weather;

    private String dayName;

    private String mood;

    private String amSchedule;

    private String pmSchedule;

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

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAmSchedule() {
        return amSchedule;
    }

    public void setAmSchedule(String amSchedule) {
        this.amSchedule = amSchedule;
    }

    public String getPmSchedule() {
        return pmSchedule;
    }

    public void setPmSchedule(String pmSchedule) {
        this.pmSchedule = pmSchedule;
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

        DaySechudlerInfoDTO daySechudlerInfoDTO = (DaySechudlerInfoDTO) o;
        if(daySechudlerInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), daySechudlerInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DaySechudlerInfoDTO{" +
            "id=" + getId() +
            ", tuanId=" + getTuanId() +
            ", time='" + getTime() + "'" +
            ", weather='" + getWeather() + "'" +
            ", dayName='" + getDayName() + "'" +
            ", mood='" + getMood() + "'" +
            ", amSchedule='" + getAmSchedule() + "'" +
            ", pmSchedule='" + getPmSchedule() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
