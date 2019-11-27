package com.protei.spring.response;

import lombok.Data;

@Data
public class SetStatusResponse {
    private Long id;
    private String oldUserStatus;
    private String newUserStatus;

    public SetStatusResponse(Long id, String oldUserStatus, String newUserStatus) {
        this.id = id;
        this.oldUserStatus = oldUserStatus;
        this.newUserStatus = newUserStatus;
    }
}
