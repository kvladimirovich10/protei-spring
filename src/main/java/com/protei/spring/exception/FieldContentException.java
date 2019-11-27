package com.protei.spring.exception;

import org.springframework.validation.BindingResult;

public class FieldContentException extends RuntimeException{
    private BindingResult bindingResult;

    public FieldContentException(BindingResult result) {
        this.bindingResult = result;
    }

    public BindingResult getResult() {
        return bindingResult;
    }
}
