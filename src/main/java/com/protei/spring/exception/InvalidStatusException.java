package com.protei.spring.exception;

public class InvalidStatusException extends IllegalArgumentException {
    public InvalidStatusException(String status) {
        super("Status '" + status + "' is not one of [ONLINE, AWAY, OFFLINE]");
    }
}
