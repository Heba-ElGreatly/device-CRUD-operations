package com.iot.admin.service;

import com.iot.dto.SIMCardDto;
import com.iot.exception.BusinessException;
import com.iot.exception.UserPermissionException;

import java.util.List;
import java.util.Optional;

public interface DeviceAdminService {

    List<SIMCardDto> getAllWaitingForActivationDevices();
    Optional<SIMCardDto> getDeviceById(Integer deviceId);
    void removeDeviceByDeviceId(Integer deviceId, Integer userId) throws UserPermissionException;
    SIMCardDto updateDeviceStatus(SIMCardDto simCardDto, Integer userId) throws BusinessException;
}
