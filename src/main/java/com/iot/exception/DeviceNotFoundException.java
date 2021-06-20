package com.iot.exception;

public class DeviceNotFoundException extends BusinessException {

    public DeviceNotFoundException() {
        super("No Device Found with this SIM number");
    }
}
