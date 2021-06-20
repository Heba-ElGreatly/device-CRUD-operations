package com.iot.controller;


import com.iot.dto.SIMCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iot.service.IotDeviceService;

import java.util.List;

@RestController
@RequestMapping("api/devices")
public class DeviceResource {

    @Autowired
    private IotDeviceService deviceService;


    @GetMapping("/")
    ResponseEntity<List<SIMCardDto>> findWaitingForActivationDevices () {//throws ItemNotFoundException {
        return new ResponseEntity<>(deviceService.getAllWaitingForActivationDevices(), HttpStatus.OK);
    }
}
