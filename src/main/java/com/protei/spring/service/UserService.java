package com.protei.spring.service;

import com.protei.spring.model.User;
import com.protei.spring.response.FullUserResponse;

public interface UserService {
    Long addUser(User user);

    FullUserResponse getUserInfoById(Long id);
}
