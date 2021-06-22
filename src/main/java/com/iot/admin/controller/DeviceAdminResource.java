package com.iot.admin.controller;

import com.iot.admin.service.DeviceAdminService;
import com.iot.dto.SIMCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update-device")
    ResponseEntity<SIMCardDto> updateDeviceStatus(@RequestBody SIMCardDto simCardDto, @Param("userId") Integer userId){
        return new ResponseEntity<>(adminService.updateDeviceStatus(simCardDto,userId), HttpStatus.OK);
    }

    @DeleteMapping("/remove-device")
    ResponseEntity<?> removeDevice(@Param("deviceId") Integer deviceId, @Param("userId") Integer userId){
        adminService.removeDeviceByDeviceId(deviceId, userId);
        return ResponseEntity.noContent().build();
    }

}
