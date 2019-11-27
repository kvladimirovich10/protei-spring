package com.protei.spring.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("No such user with id = " + userId);
    }
}
