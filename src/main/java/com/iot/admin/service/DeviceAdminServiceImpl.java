package com.iot.admin.service;

import com.iot.admin.dao.DeviceAdminRepository;
import com.iot.dto.SIMCardDto;
import com.iot.exception.DeviceNotFoundException;
import com.iot.exception.UpdateDeviceStatusException;
import com.iot.exception.WaitingForActivationDeviceNotFoundException;
import com.iot.mapper.SIMCardMapper;
import com.iot.model.SIMCard;
import com.iot.service.IotDeviceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeviceAdminServiceImpl implements DeviceAdminService {

    @Autowired
    private IotDeviceService deviceService;

    @Autowired
    private DeviceAdminRepository adminRepository;

    @Autowired
    private SIMCardMapper simCardMapper;


    @Override
    public List<SIMCardDto> getAllWaitingForActivationDevices() {
        return deviceService.getAllWaitingForActivationDevices();
    }

    @Override
    public void removeDevice(Integer deviceId) {
        adminRepository.deleteById(deviceId);
    }

    @Override
    public SIMCardDto updateDeviceStatus(SIMCardDto simCardDto) throws UpdateDeviceStatusException {
        SIMCard existingSimCard = adminRepository.findBySIMNumberAndOperationCode()
                .orElseThrow(() -> new DeviceNotFoundException());

        SIMCard simToBeUpdated = new SIMCard();
        simToBeUpdated.setStatus(simCardDto.getStatus());
        simToBeUpdated.setCountryName(existingSimCard.getCountryName());
        simToBeUpdated.setSimNumber(existingSimCard.getSimNumber());
        simToBeUpdated.setOperation(existingSimCard.getOperation());

        SIMCard updatedDeviceStatus = adminRepository.save(simToBeUpdated);
        return simCardMapper.mapEntityToDTO(updatedDeviceStatus);
    }


}
