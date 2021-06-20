package com.iot.admin.service;

import com.iot.dto.SIMCardDto;
import com.iot.exception.UpdateDeviceStatusException;

import java.util.List;

public interface DeviceAdminService {

    List<SIMCardDto> getAllWaitingForActivationDevices();
    //remove device
    void removeDevice(Integer deviceId);

    //update device status
    SIMCardDto updateDeviceStatus(SIMCardDto simCardDto) throws UpdateDeviceStatusException;
}
