package com.iot;

import com.iot.dao.IotDeviceRepository;
import com.iot.model.Operation;
import com.iot.model.SIMCard;
import com.iot.service.IotDeviceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IotDeviceAPITests {

    @Autowired
    private IotDeviceService deviceService;

    @MockBean
    private IotDeviceRepository iotDeviceRepository;


    @Test
    public void getWaitingForActivationDevicesTest(){
        when(iotDeviceRepository.getAllWaitingForActivationDevices())
                .thenReturn(java.util.Optional.of(Stream
                        .of(SIMCard.builder().id(5)
                                .simNumber("762452")
                                .countryName("USA")
                                .status("Waiting For Activation")
                                .operation(Operation.builder()
                                        .id(5)
                                        .operationName("vodafone").operationCode("010")
                                .build()).build()).collect(Collectors.toList())));
        assertEquals(1,deviceService.getAllWaitingForActivationDevices().size());
    }


}
