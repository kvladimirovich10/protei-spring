package com.protei.spring.service;

import com.protei.spring.exception.UserNotFoundException;
import com.protei.spring.model.User;
import com.protei.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Long addUser(User user) {
        userRepository.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public User getUserById(Long userId) throws RuntimeException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user;
    }
}
