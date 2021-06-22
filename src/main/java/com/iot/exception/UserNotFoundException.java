package com.iot.exception;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super("User Not Found");
    }
}
