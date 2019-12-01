package com.protei.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.protei.spring.controller.UserController;
import com.protei.spring.exception.InvalidStatusException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Entity
@Data
@Table(name = "user_status_table")
public class UserStatus {

    private static Logger log = Logger.getLogger(UserStatus.class.getName());

    public enum StatusEnum {
        ONLINE, AWAY, OFFLINE, NO_STATUS
    }

    @Id
    @Column(name="user_id", unique = true, nullable = false)
    private Long id;

    @NotNull
    private String userStatus;

    @JsonIgnore
    private LocalDateTime lastTimeStatusChangeTime;

    public UserStatus(Long id, StatusEnum status) {
        this.id = id;
        this.userStatus = status.name();
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

    public void validateEnumStatus() {
        try {
            StatusEnum.valueOf(this.userStatus);
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException(this.userStatus);
        }
    }

    public StatusEnum getUserStatus() {
        return StatusEnum.valueOf(this.userStatus);
    }

    public  void setNewUserStatus(StatusEnum status) {
        this.userStatus = status.name();
    }
}
