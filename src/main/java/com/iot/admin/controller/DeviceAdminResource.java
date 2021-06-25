package com.iot.admin.controller;

import com.iot.admin.service.DeviceAdminService;
import com.iot.dto.SIMCardDto;
import com.iot.exception.DeviceNotFoundException;
import com.iot.exception.UserPermissionException;
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

    @GetMapping("/get/{id}")
    ResponseEntity<SIMCardDto> getIotDevice(@PathVariable("id") Integer deviceId){
        return ResponseEntity.ok(adminService.getDeviceById(deviceId).orElseThrow(() -> new DeviceNotFoundException()));
    }

    @PutMapping("/update-device")
    ResponseEntity<SIMCardDto> updateDeviceStatus(@RequestBody SIMCardDto simCardDto, @Param("userId") Integer userId){
        return new ResponseEntity<>(adminService.updateDeviceStatus(simCardDto,userId), HttpStatus.OK);
    }

    @DeleteMapping("/remove-device")
    ResponseEntity<?> removeDevice(@Param("deviceId") Integer deviceId, @Param("userId") Integer userId) throws UserPermissionException {
        adminService.removeDeviceByDeviceId(deviceId, userId);
        return ResponseEntity.noContent().build();
    }

}
