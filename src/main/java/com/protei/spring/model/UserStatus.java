package com.protei.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_status_table")
public class UserStatus {

    public enum Status {
        ONLINE, AWAY, OFFLINE, NO_STATUS
    }

    @Id
    @Column(name="user_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private Status userStatus;

    @JsonIgnore
    private LocalDateTime lastTimeStatusChangeTime;

    public UserStatus(Long id, Status userStatus) {
        this.id = id;
        this.userStatus = userStatus;
        this.lastTimeStatusChangeTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "id=" + id +
                ", userStatus=" + userStatus +
                '}';
    }

    public UserStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Status userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getLastTimeStatusChangeTime() {
        return lastTimeStatusChangeTime;
    }

    public void setLastTimeStatusChangeTime() {
        this.lastTimeStatusChangeTime = LocalDateTime.now();
    }
}
