package com.iot.exception;

public class UserPermissionException extends BusinessException {

    public UserPermissionException() {
        super("User has no permission to update device status");
    }
}
