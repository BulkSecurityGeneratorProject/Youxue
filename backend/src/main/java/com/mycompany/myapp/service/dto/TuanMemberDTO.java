package com.mycompany.myapp.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the TuanMember entity.
 */
public class TuanMemberDTO implements Serializable {

    private Long id;

    private Long tuanId;

    private String memberType;

    private String memberDescrption;

    private Long yearsOld;

    private String sex;

    private String fromCity;

    private Instant joinTime;

    private String email;

    private String phoneNumber;

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

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberDescrption() {
        return memberDescrption;
    }

    public void setMemberDescrption(String memberDescrption) {
        this.memberDescrption = memberDescrption;
    }

    public Long getYearsOld() {
        return yearsOld;
    }

    public void setYearsOld(Long yearsOld) {
        this.yearsOld = yearsOld;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public Instant getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Instant joinTime) {
        this.joinTime = joinTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

        TuanMemberDTO tuanMemberDTO = (TuanMemberDTO) o;
        if(tuanMemberDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tuanMemberDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TuanMemberDTO{" +
            "id=" + getId() +
            ", tuanId=" + getTuanId() +
            ", memberType='" + getMemberType() + "'" +
            ", memberDescrption='" + getMemberDescrption() + "'" +
            ", yearsOld=" + getYearsOld() +
            ", sex='" + getSex() + "'" +
            ", fromCity='" + getFromCity() + "'" +
            ", joinTime='" + getJoinTime() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", creator='" + getCreator() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
