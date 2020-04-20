package com.protei.spring.response;

public class SetStatusResponse {
    private Long id;
    private String oldUserStatus;
    private String newUserStatus;

    public SetStatusResponse(Long id, String oldUserStatus, String newUserStatus) {
        this.id = id;
        this.oldUserStatus = oldUserStatus;
        this.newUserStatus = newUserStatus;
    }

    @Override
    public String toString() {
        return "SetStatusResponse{" +
                "id=" + id +
                ", oldUserStatus='" + oldUserStatus + '\'' +
                ", newUserStatus='" + newUserStatus + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getOldUserStatus() {
        return oldUserStatus;
    }

    public String getNewUserStatus() {
        return newUserStatus;
    }
}
