package com.iot.admin.controller;

import com.iot.admin.service.DeviceAdminService;
import com.iot.dto.SIMCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class DeviceAdminResource {

    @Autowired
    private DeviceAdminService adminService;

    @GetMapping("/")
    ResponseEntity<List<SIMCardDto>> findWaitingForActivationDevices () {
        return new ResponseEntity<>(adminService.getAllWaitingForActivationDevices(), HttpStatus.OK);
    }

}
