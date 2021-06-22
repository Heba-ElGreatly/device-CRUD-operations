package com.iot.admin.service;

import com.iot.dto.SIMCardDto;
import com.iot.exception.BusinessException;
import com.iot.model.SIMCard;

import java.util.List;

public interface DeviceAdminService {

    List<SIMCardDto> getAllWaitingForActivationDevices();

    //remove device
    void removeDeviceByDeviceId(Integer deviceId, Integer userId);

    //update device status
    SIMCardDto updateDeviceStatus(SIMCardDto simCardDto, Integer userId) throws BusinessException;
}
