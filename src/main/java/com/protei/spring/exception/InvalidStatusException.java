package com.protei.spring.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidStatusException extends IllegalArgumentException {
    public InvalidStatusException(String status) {
        super("Status '" + status + "' is not one of [ONLINE, AWAY, OFFLINE]");
    }


}
