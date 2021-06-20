package com.iot.service;

import com.iot.dao.IotDeviceRepository;
import com.iot.dto.SIMCardDto;
import com.iot.exception.WaitingForActivationDeviceNotFoundException;
import com.iot.mapper.SIMCardMapper;
import com.iot.model.SIMCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IotDeviceServiceImpl implements IotDeviceService {

    @Autowired
    private IotDeviceRepository iotDeviceRepository;

    @Autowired
    private SIMCardMapper simCardMapper;


    @Override
    public List<SIMCardDto> getAllWaitingForActivationDevices() {
        List<SIMCard> waitingForActivationDevices = iotDeviceRepository.getAllWaitingForActivationDevices()
                .orElseThrow(() -> new WaitingForActivationDeviceNotFoundException());

        return waitingForActivationDevices.stream().map(device -> simCardMapper.mapEntityToDTO(device)).collect(Collectors.toList());
    }
}
