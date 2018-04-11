package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A TuanMember.
 */
@Entity
@Table(name = "tuan_member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TuanMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tuan_id")
    private Long tuanId;

    @Column(name = "member_type")
    private String memberType;

    @Column(name = "member_descrption")
    private String memberDescrption;

    @Column(name = "years_old")
    private Long yearsOld;

    @Column(name = "sex")
    private String sex;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "join_time")
    private Instant joinTime;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

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

    public TuanMember tuanId(Long tuanId) {
        this.tuanId = tuanId;
        return this;
    }

    public void setTuanId(Long tuanId) {
        this.tuanId = tuanId;
    }

    public String getMemberType() {
        return memberType;
    }

    public TuanMember memberType(String memberType) {
        this.memberType = memberType;
        return this;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberDescrption() {
        return memberDescrption;
    }

    public TuanMember memberDescrption(String memberDescrption) {
        this.memberDescrption = memberDescrption;
        return this;
    }

    public void setMemberDescrption(String memberDescrption) {
        this.memberDescrption = memberDescrption;
    }

    public Long getYearsOld() {
        return yearsOld;
    }

    public TuanMember yearsOld(Long yearsOld) {
        this.yearsOld = yearsOld;
        return this;
    }

    public void setYearsOld(Long yearsOld) {
        this.yearsOld = yearsOld;
    }

    public String getSex() {
        return sex;
    }

    public TuanMember sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFromCity() {
        return fromCity;
    }

    public TuanMember fromCity(String fromCity) {
        this.fromCity = fromCity;
        return this;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public Instant getJoinTime() {
        return joinTime;
    }

    public TuanMember joinTime(Instant joinTime) {
        this.joinTime = joinTime;
        return this;
    }

    public void setJoinTime(Instant joinTime) {
        this.joinTime = joinTime;
    }

    public String getEmail() {
        return email;
    }

    public TuanMember email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public TuanMember phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreator() {
        return creator;
    }

    public TuanMember creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public TuanMember createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public TuanMember updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public TuanInfo getTuanInfo() {
        return tuanInfo;
    }

    public TuanMember tuanInfo(TuanInfo tuanInfo) {
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
        TuanMember tuanMember = (TuanMember) o;
        if (tuanMember.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tuanMember.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TuanMember{" +
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
