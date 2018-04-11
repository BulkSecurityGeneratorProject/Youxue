package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "tuan_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TuanInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leader")
    private String leader;

    @Column(name = "tuan_name")
    private String tuanName;

    @Column(name = "tuan_descrption")
    private String tuanDescrption;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "team_member_count")
    private Long teamMemberCount;

    @Column(name = "tuan_start_time")
    private Instant tuanStartTime;

    @Column(name = "tuan_end_time")
    private Instant tuanEndTime;

    @Column(name = "tuan_creat_time")
    private Instant tuanCreatTime;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @OneToMany(mappedBy = "tuanInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TuanMember> tuanmembers = new HashSet<>();

    @OneToMany(mappedBy = "tuanInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DaySechudlerInfo> daysechudlerinfos = new HashSet<>();

    @OneToMany(mappedBy = "tuanInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Message> messages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeader() {
        return leader;
    }

    public TuanInfo leader(String leader) {
        this.leader = leader;
        return this;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getTuanName() {
        return tuanName;
    }

    public TuanInfo tuanName(String tuanName) {
        this.tuanName = tuanName;
        return this;
    }

    public void setTuanName(String tuanName) {
        this.tuanName = tuanName;
    }

    public String getTuanDescrption() {
        return tuanDescrption;
    }

    public TuanInfo tuanDescrption(String tuanDescrption) {
        this.tuanDescrption = tuanDescrption;
        return this;
    }

    public void setTuanDescrption(String tuanDescrption) {
        this.tuanDescrption = tuanDescrption;
    }

    public String getCity() {
        return city;
    }

    public TuanInfo city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public TuanInfo country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getTeamMemberCount() {
        return teamMemberCount;
    }

    public TuanInfo teamMemberCount(Long teamMemberCount) {
        this.teamMemberCount = teamMemberCount;
        return this;
    }

    public void setTeamMemberCount(Long teamMemberCount) {
        this.teamMemberCount = teamMemberCount;
    }

    public Instant getTuanStartTime() {
        return tuanStartTime;
    }

    public TuanInfo tuanStartTime(Instant tuanStartTime) {
        this.tuanStartTime = tuanStartTime;
        return this;
    }

    public void setTuanStartTime(Instant tuanStartTime) {
        this.tuanStartTime = tuanStartTime;
    }

    public Instant getTuanEndTime() {
        return tuanEndTime;
    }

    public TuanInfo tuanEndTime(Instant tuanEndTime) {
        this.tuanEndTime = tuanEndTime;
        return this;
    }

    public void setTuanEndTime(Instant tuanEndTime) {
        this.tuanEndTime = tuanEndTime;
    }

    public Instant getTuanCreatTime() {
        return tuanCreatTime;
    }

    public TuanInfo tuanCreatTime(Instant tuanCreatTime) {
        this.tuanCreatTime = tuanCreatTime;
        return this;
    }

    public void setTuanCreatTime(Instant tuanCreatTime) {
        this.tuanCreatTime = tuanCreatTime;
    }

    public String getCreator() {
        return creator;
    }

    public TuanInfo creator(String creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public TuanInfo createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public TuanInfo updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Set<TuanMember> getTuanmembers() {
        return tuanmembers;
    }

    public TuanInfo tuanmembers(Set<TuanMember> tuanMembers) {
        this.tuanmembers = tuanMembers;
        return this;
    }

    public TuanInfo addTuanmember(TuanMember tuanMember) {
        this.tuanmembers.add(tuanMember);
        tuanMember.setTuanInfo(this);
        return this;
    }

    public TuanInfo removeTuanmember(TuanMember tuanMember) {
        this.tuanmembers.remove(tuanMember);
        tuanMember.setTuanInfo(null);
        return this;
    }

    public void setTuanmembers(Set<TuanMember> tuanMembers) {
        this.tuanmembers = tuanMembers;
    }

    public Set<DaySechudlerInfo> getDaysechudlerinfos() {
        return daysechudlerinfos;
    }

    public TuanInfo daysechudlerinfos(Set<DaySechudlerInfo> daySechudlerInfos) {
        this.daysechudlerinfos = daySechudlerInfos;
        return this;
    }

    public TuanInfo addDaysechudlerinfo(DaySechudlerInfo daySechudlerInfo) {
        this.daysechudlerinfos.add(daySechudlerInfo);
        daySechudlerInfo.setTuanInfo(this);
        return this;
    }

    public TuanInfo removeDaysechudlerinfo(DaySechudlerInfo daySechudlerInfo) {
        this.daysechudlerinfos.remove(daySechudlerInfo);
        daySechudlerInfo.setTuanInfo(null);
        return this;
    }

    public void setDaysechudlerinfos(Set<DaySechudlerInfo> daySechudlerInfos) {
        this.daysechudlerinfos = daySechudlerInfos;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public TuanInfo messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public TuanInfo addMessage(Message message) {
        this.messages.add(message);
        message.setTuanInfo(this);
        return this;
    }

    public TuanInfo removeMessage(Message message) {
        this.messages.remove(message);
        message.setTuanInfo(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
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
        TuanInfo tuanInfo = (TuanInfo) o;
        if (tuanInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tuanInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TuanInfo{" +
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
