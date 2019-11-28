package com.protei.spring.serviceImpl;

import com.protei.spring.exception.UserNotFoundException;
import com.protei.spring.model.User;
import com.protei.spring.model.UserStatus;
import com.protei.spring.repository.UserRepository;
import com.protei.spring.response.FullUserResponse;
import com.protei.spring.service.UserService;
import com.protei.spring.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatusService userStatusService;

    private UserServiceImpl() {}

    public static UserServiceImpl getUserServiceImpl() {
        return new UserServiceImpl();
    }

    @Transactional
    @Override
    public Long addUser(User user) {
        userRepository.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public FullUserResponse getUserInfoById(Long userId) throws RuntimeException {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        UserStatus userStatus = userStatusService.getUserStatusByUserId(user.getId());

        return new FullUserResponse(user, userStatus.getUserStatus().name());
    }
}
