package com.iot;

import com.iot.admin.dao.DeviceAdminRepository;
import com.iot.admin.service.DeviceAdminService;
import com.iot.dao.UserRepository;
import com.iot.dto.OperationDto;
import com.iot.dto.SIMCardDto;
import com.iot.exception.UserPermissionException;
import com.iot.model.Operation;
import com.iot.model.SIMCard;
import com.iot.model.User;
import com.iot.service.IotDeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IotDeviceAdminAPITests {

    @Autowired
    private DeviceAdminService adminService;

    @Autowired
    private IotDeviceService deviceService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private DeviceAdminRepository adminRepository;

    @Test
    public void updateDeviceStatusTest() {

        SIMCardDto dto = SIMCardDto.builder()
                .simNumber("847331")
                .countryName("Russia")
                .status("Activated")
                .operation(OperationDto.builder()
                        .operationName("vodafone")
                        .operationCode("010").build())
                .build();

        SIMCard entity = SIMCard.builder()
                .id(6)
                .simNumber("847331")
                .countryName("Russia")
                .status("Activated")
                .operation(Operation.builder().id(1)
                        .operationCode("010")
                        .operationName("vodafone")
                        .build()
                ).build();

        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(User.builder()
                .id(1)
                .email("heba@gmail.com")
                .name("heba")
                .role("admin").build()));

        when(adminRepository.findBySIMNumberAndOperationCode("847331", "010"))
                .thenReturn(java.util.Optional.ofNullable(SIMCard.builder()
                        .id(6)
                        .simNumber("847331")
                        .countryName("Russia")
                        .status("Activated")
                        .operation(Operation.builder().id(1)
                                .operationCode("010")
                                .operationName("vodafone")
                                .build()
                        ).build()));

        when(adminRepository.save(entity)).thenReturn(
                SIMCard.builder()
                        .simNumber("847331")
                        .countryName("Russia")
                        .status("Deactivated")
                        .operation(Operation.builder()
                                .id(1)
                                .operationName("vodafone")
                                .operationCode("010").build())
                        .build()
        );

        assertEquals("Deactivated", adminService.updateDeviceStatus(dto, 1).getStatus());
    }

    @Test
    public void deviceStatusIsNotUpdatedTest() {
        SIMCardDto dto = SIMCardDto.builder()
                .simNumber("847331")
                .countryName("Russia")
                .status("Activated")
                .operation(OperationDto.builder()
                        .operationName("vodafone")
                        .operationCode("010").build())
                .build();

        SIMCard entity = SIMCard.builder()
                .id(6)
                .simNumber("847331")
                .countryName("Russia")
                .status("Activated")
                .operation(Operation.builder().id(1)
                        .operationCode("010")
                        .operationName("vodafone")
                        .build()
                ).build();

        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(User.builder()
                .id(1)
                .email("heba@gmail.com")
                .name("heba")
                .role("user").build()));

        when(adminRepository.findBySIMNumberAndOperationCode("847331", "010"))
                .thenReturn(java.util.Optional.ofNullable(SIMCard.builder()
                        .id(6)
                        .simNumber("847331")
                        .countryName("Russia")
                        .status("Activated")
                        .operation(Operation.builder().id(1)
                                .operationCode("010")
                                .operationName("vodafone")
                                .build()
                        ).build()));

        when(adminRepository.save(entity)).thenReturn(
                SIMCard.builder()
                        .simNumber("847331")
                        .countryName("Russia")
                        .status("Deactivated")
                        .operation(Operation.builder()
                                .id(1)
                                .operationName("vodafone")
                                .operationCode("010").build())
                        .build()
        );

        assertThrows(UserPermissionException.class, () -> adminService.updateDeviceStatus(dto, 1));
    }

    @Test
    public void addDeviceTest() {
        SIMCard entity = SIMCard.builder()
                .id(6)
                .simNumber("847331")
                .countryName("Russia")
                .status("Activated")
                .operation(Operation.builder().id(1)
                        .operationCode("010")
                        .operationName("vodafone")
                        .build()
                ).build();
        when(adminRepository.save(entity)).thenReturn(entity);
        assertEquals(entity, adminRepository.save(entity));
    }

    @Test
    public void removeDeviceByDeviceIdTest() {
        SIMCard entity = SIMCard.builder()
                .id(6)
                .simNumber("847331")
                .countryName("Russia")
                .status("Activated")
                .operation(Operation.builder().id(1)
                        .operationCode("010")
                        .operationName("vodafone")
                        .build()
                ).build();

        when(userRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(User.builder()
                .id(1)
                .email("heba@gmail.com")
                .name("heba")
                .role("admin").build()));

        adminService.removeDeviceByDeviceId(6, 1);
        verify(adminRepository, times(1)).deleteById(6);
    }

    @Test
    public void test() {
        when(adminRepository.findBySIMNumberAndOperationCode("847331", "010"))
                .thenReturn(java.util.Optional.ofNullable(SIMCard.builder()
                        .id(6)
                        .simNumber("847331")
                        .countryName("Russia")
                        .status("Activated")
                        .operation(Operation.builder().id(1)
                                .operationCode("010")
                                .operationName("vodafone")
                                .build()
                        ).build()));
        assertEquals("Activated",
                adminRepository.findBySIMNumberAndOperationCode("847331", "010")
                        .get().getStatus());
    }
}
