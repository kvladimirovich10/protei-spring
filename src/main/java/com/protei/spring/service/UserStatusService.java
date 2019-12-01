package com.protei.spring.service;

import com.protei.spring.model.UserStatus;
import com.protei.spring.response.SetStatusResponse;

public interface UserStatusService {
    SetStatusResponse setUserStatus(UserStatus status);

    UserStatus getUserStatusByUserId(Long userId);
}
