package com.protei.spring.service;

import com.protei.spring.model.UserStatus;
import com.protei.spring.repository.UserStatusRepository;
import com.protei.spring.response.SetStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    UserService userService;

    private UserStatusServiceImpl() {}

    public static UserStatusServiceImpl getUserStatusServiceImpl() {
        return new UserStatusServiceImpl();
    }

    @Override
    public SetStatusResponse setStatus(UserStatus status) {

        UserStatus oldUserStatus = userStatusRepository.findUserStatusById(status.getId()).orElse(new UserStatus(status.getId(), UserStatus.Status.NO_STATUS));
        userStatusRepository.saveAndFlush(status);

        return new SetStatusResponse(
                status.getId(),
                oldUserStatus.getUserStatus().name(),
                status.getUserStatus().name());

    }

    @Override
    public UserStatus getUserStatusByUserId(Long userId) {
        return userStatusRepository.findUserStatusById(userId).orElse(new UserStatus(userId, UserStatus.Status.NO_STATUS));
    }
}
