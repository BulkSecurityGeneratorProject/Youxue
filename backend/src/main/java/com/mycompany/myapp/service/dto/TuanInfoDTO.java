package com.mycompany.myapp.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the TuanInfo entity.
 */
public class TuanInfoDTO implements Serializable {

    private Long id;

    private String leader;

    private String tuanName;

    private String tuanDescrption;

    private String city;

    private String country;

    private Long teamMemberCount;

    private Instant tuanStartTime;

    private Instant tuanEndTime;

    private Instant tuanCreatTime;

    private String creator;

    private Instant createDate;

    private Instant updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getTuanName() {
        return tuanName;
    }

    public void setTuanName(String tuanName) {
        this.tuanName = tuanName;
    }

    public String getTuanDescrption() {
        return tuanDescrption;
    }

    public void setTuanDescrption(String tuanDescrption) {
        this.tuanDescrption = tuanDescrption;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTeamMemberCount() {
        return teamMemberCount;
    }

    public void setTeamMemberCount(Long teamMemberCount) {
        this.teamMemberCount = teamMemberCount;
    }

    public Instant getTuanStartTime() {
        return tuanStartTime;
    }

    public void setTuanStartTime(Instant tuanStartTime) {
        this.tuanStartTime = tuanStartTime;
    }

    public Instant getTuanEndTime() {
        return tuanEndTime;
    }

    public void setTuanEndTime(Instant tuanEndTime) {
        this.tuanEndTime = tuanEndTime;
    }

    public Instant getTuanCreatTime() {
        return tuanCreatTime;
    }

    public void setTuanCreatTime(Instant tuanCreatTime) {
        this.tuanCreatTime = tuanCreatTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TuanInfoDTO tuanInfoDTO = (TuanInfoDTO) o;
        if(tuanInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tuanInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TuanInfoDTO{" +
            "id=" + getId() +
            ", leader='" + getLeader() + "'" +
            ", tuanName='" + getTuanName() + "'" +
            ", tuanDescrption='" + getTuanDescrption() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", teamMemberCount=" + getTeamMemberCount() +
            ", tuanStartTime='" + getTuanStartTime() + "'" +
            ", tuanEndTime='" + getTuanEndTime() + "'" +
            ", tuanCreatTime='" + getTuanCreatTime() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
