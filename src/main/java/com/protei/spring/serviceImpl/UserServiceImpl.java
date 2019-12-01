package com.protei.spring.serviceImpl;

import com.protei.spring.controller.UserController;
import com.protei.spring.exception.UserAlreadyExistsException;
import com.protei.spring.exception.UserNotFoundException;
import com.protei.spring.model.User;
import com.protei.spring.model.UserStatus;
import com.protei.spring.repository.UserRepository;
import com.protei.spring.repository.UserStatusRepository;
import com.protei.spring.response.FullUserResponse;
import com.protei.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.logging.Logger;

import static com.protei.spring.model.UserStatus.StatusEnum.NO_STATUS;

@Service
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    private UserServiceImpl() {}

    public static UserServiceImpl getUserServiceImpl() {
        return new UserServiceImpl();
    }

    @Transactional
    @Override
    public Long addUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        userRepository.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public FullUserResponse getUserInfoById(Long userId) throws RuntimeException {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        UserStatus userStatus = userStatusRepository.findUserStatusById(userId).
                orElse(new UserStatus(userId, NO_STATUS));

        return new FullUserResponse(user, userStatus.getUserStatus().name());
    }
}
