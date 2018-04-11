package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DaySechudlerInfo.
 */
@Entity
@Table(name = "day_sechudler_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DaySechudlerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tuan_id")
    private Long tuanId;

    @Column(name = "jhi_time")
    private Instant time;

    @Column(name = "weather")
    private String weather;

    @Column(name = "day_name")
    private String dayName;

    @Column(name = "mood")
    private String mood;

    @Column(name = "am_schedule")
    private String amSchedule;

    @Column(name = "pm_schedule")
    private String pmSchedule;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @ManyToOne
    private TuanInfo tuanInfo;

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

    public DaySechudlerInfo tuanId(Long tuanId) {
        this.tuanId = tuanId;
        return this;
    }

    public void setTuanId(Long tuanId) {
        this.tuanId = tuanId;
    }

    public Instant getTime() {
        return time;
    }

    public DaySechudlerInfo time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public DaySechudlerInfo weather(String weather) {
        this.weather = weather;
        return this;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDayName() {
        return dayName;
    }

    public DaySechudlerInfo dayName(String dayName) {
        this.dayName = dayName;
        return this;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMood() {
        return mood;
    }

    public DaySechudlerInfo mood(String mood) {
        this.mood = mood;
        return this;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAmSchedule() {
        return amSchedule;
    }

    public DaySechudlerInfo amSchedule(String amSchedule) {
        this.amSchedule = amSchedule;
        return this;
    }

    public void setAmSchedule(String amSchedule) {
        this.amSchedule = amSchedule;
    }

    public String getPmSchedule() {
        return pmSchedule;
    }

    public DaySechudlerInfo pmSchedule(String pmSchedule) {
        this.pmSchedule = pmSchedule;
        return this;
    }

    public void setPmSchedule(String pmSchedule) {
        this.pmSchedule = pmSchedule;
    }

    public String getCreator() {
        return creator;
    }

    public DaySechudlerInfo creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public DaySechudlerInfo createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public DaySechudlerInfo updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public TuanInfo getTuanInfo() {
        return tuanInfo;
    }

    public DaySechudlerInfo tuanInfo(TuanInfo tuanInfo) {
        this.tuanInfo = tuanInfo;
        return this;
    }

    public void setTuanInfo(TuanInfo tuanInfo) {
        this.tuanInfo = tuanInfo;
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
        DaySechudlerInfo daySechudlerInfo = (DaySechudlerInfo) o;
        if (daySechudlerInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), daySechudlerInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DaySechudlerInfo{" +
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
