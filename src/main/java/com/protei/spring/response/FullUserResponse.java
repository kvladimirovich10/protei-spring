package com.protei.spring.response;

import com.protei.spring.model.User;

public class FullUserResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String status;

    public FullUserResponse(User user, String status) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }
}
