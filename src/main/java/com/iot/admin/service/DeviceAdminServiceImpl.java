package com.iot.admin.service;

import com.iot.admin.dao.DeviceAdminRepository;
import com.iot.dao.UserRepository;
import com.iot.dto.SIMCardDto;
import com.iot.exception.BusinessException;
import com.iot.exception.DeviceNotFoundException;
import com.iot.exception.UserNotFoundException;
import com.iot.exception.UserPermissionException;
import com.iot.mapper.SIMCardMapper;
import com.iot.model.SIMCard;
import com.iot.model.User;
import com.iot.service.IotDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceAdminServiceImpl implements DeviceAdminService {

    private static final String USER_ADMIN_ROLE = "Admin";

    @Autowired
    private UserRepository userRepository;

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
    public void removeDeviceByDeviceId(Integer deviceId, Integer userId) {
        checkingUserPermissions(userId);
        adminRepository.deleteById(deviceId);
    }

    @Override
    public SIMCardDto updateDeviceStatus(SIMCardDto simCardDto, Integer userId) throws BusinessException {

        checkingUserPermissions(userId);

        SIMCard existingSimCard = adminRepository.findBySIMNumberAndOperationCode(simCardDto.getSimNumber(), simCardDto.getOperation().getOperationCode())
                .orElseThrow(() -> new DeviceNotFoundException());

        existingSimCard.setId(existingSimCard.getId());
        existingSimCard.setStatus(simCardDto.getStatus());
        existingSimCard.setCountryName(existingSimCard.getCountryName());
        existingSimCard.setSimNumber(existingSimCard.getSimNumber());
        existingSimCard.setOperation(existingSimCard.getOperation());

        SIMCard updatedDevice = adminRepository.save(existingSimCard);
        return simCardMapper.mapEntityToDTO(updatedDevice);
    }

    private void checkingUserPermissions(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        if (!user.getRole().equalsIgnoreCase(USER_ADMIN_ROLE)) {
            throw new UserPermissionException();
        }
    }
}
