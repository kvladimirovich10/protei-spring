package com.protei.spring.response;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

    private String exceptionClass;
    private List<String> messages = new ArrayList<>();

    public ExceptionResponse(String exceptionClass, String message) {
        this.exceptionClass = exceptionClass;
        this.messages.add(message);
    }

    public ExceptionResponse(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public void addExceptionMessage(String message) {
        messages.add(message);
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public List<String> getMessages() {
        return messages;
    }
}
