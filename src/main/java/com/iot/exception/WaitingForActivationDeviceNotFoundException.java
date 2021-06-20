package com.iot.exception;

public class WaitingForActivationDeviceNotFoundException extends BusinessException {

    public WaitingForActivationDeviceNotFoundException() {
        super("No Device With Status 'Waiting For Activation' Found");
    }
}
