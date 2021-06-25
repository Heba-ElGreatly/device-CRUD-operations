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
import java.util.Optional;

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
    public Optional<SIMCardDto> getDeviceById(Integer deviceId) {
//        return Optional.ofNullable(adminRepository.findById(deviceId)
//                .orElseThrow(() -> new UserNotFoundException()));
        return Optional.ofNullable(deviceService.getDeviceByDeviceId(deviceId));
    }

    @Override
    public void removeDeviceByDeviceId(Integer deviceId, Integer userId) throws UserPermissionException{
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

        return simCardMapper.mapEntityToDTO(adminRepository.save(existingSimCard));
    }

    private void checkingUserPermissions(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        if (!user.getRole().equalsIgnoreCase(USER_ADMIN_ROLE)) {
            throw new UserPermissionException();
        }
    }
}
