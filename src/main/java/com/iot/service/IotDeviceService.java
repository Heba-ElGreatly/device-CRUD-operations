package com.iot.service;

import com.iot.dto.SIMCardDto;
import com.iot.exception.WaitingForActivationDeviceNotFoundException;

import java.util.List;

public interface IotDeviceService {

    List<SIMCardDto> getAllWaitingForActivationDevices();
    SIMCardDto getDeviceByDeviceId(Integer deviceId);
}
