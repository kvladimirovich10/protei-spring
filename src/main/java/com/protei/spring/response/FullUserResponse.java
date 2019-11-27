package com.protei.spring.response;

import com.protei.spring.model.User;
import lombok.Data;


@Data
public class FullUserResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String status;

    public FullUserResponse(User user, String status) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
        this.status = status;
    }
}
