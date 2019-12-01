package com.protei.spring.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("No such user with requested id = " + id);
    }
}
